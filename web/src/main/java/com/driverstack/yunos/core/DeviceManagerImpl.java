package com.driverstack.yunos.core;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.driverstack.yunos.ExecutionEnvironment;
import com.driverstack.yunos.dao.DeviceDao;
import com.driverstack.yunos.domain.ConfigurationItem;
import com.driverstack.yunos.domain.Device;
import com.driverstack.yunos.driver.Driver;
import com.driverstack.yunos.driver.config.ConfigurationItemPrimaryType;
import com.driverstack.yunos.driver.device.DeviceInfo;
import com.driverstack.yunos.driver.device.FunctionalDevice;
import com.driverstack.yunos.driver.device.PhysicalDevice;

/**
 * device manager responsibilities:
 * <ul>
 * <li>maintain device links</li>
 * <li>load/save from/to DB</li>
 * <li>cache devices</li>
 * </ul>
 * 
 * @author jackding
 * 
 */
@Component("deviceManager")
public class DeviceManagerImpl implements DeviceManager {
	private Logger logger = Logger.getLogger(DeviceManagerImpl.class);

	@Autowired
	private DeviceDao deviceDao;
	@Autowired
	private DriverManager driverManager;

	@Autowired
	private ExecutionEnvironment kernel;

	// memory cache
	Map<String, PhysicalDevice> devices = new HashMap<String, PhysicalDevice>();

	public void setDriverManager(DriverManagerImpl driverManager) {
		this.driverManager = driverManager;
	}

	public void setDeviceDao(DeviceDao deviceDao) {
		this.deviceDao = deviceDao;
	}

	public void setKernel(ExecutionEnvironment kernel) {
		this.kernel = kernel;
	}

	public PhysicalDevice getPhysicalDeviceObject(Device domainDevice) {

		String deviceId = domainDevice.getId();
		if (devices.containsKey(deviceId))
			return devices.get(deviceId);
		else {
			PhysicalDevice device = loadDevice(domainDevice);
			return device;
		}

	}

	private PhysicalDevice loadDevice(Device domainDevice) {

		Driver runtimeDriver = driverManager.loadDriver(domainDevice);
		try {
			Object config = createConfig(domainDevice,
					runtimeDriver.getConfigureClass());

			DeviceInfo devInfo = domainDevice.getInfo();
			devInfo.setConfigure(config);

			PhysicalDevice physicalDevice = runtimeDriver.createDevice(kernel,
					devInfo);

			return physicalDevice;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	private Object createConfig(Device domainDevice, Class configClass)
			throws InstantiationException, IllegalAccessException,
			InvocationTargetException {
		// create config object and set attributes.
		Object config = configClass.newInstance();
		Map<String, Object> map = new HashMap<String, Object>();
		for (ConfigurationItem ci : domainDevice.getUserConfigurationItems()
				.values()) {

			Object valueObject = null;
			if (ci.getType() == ConfigurationItemPrimaryType.DEVICE) {
				String str = ci.getValue();
				if (str != null && !str.isEmpty()) {
					String[] parts = str.split(":");
					String fdDeviceId = parts[0];
					String fdIndexStr = parts[1];
					int fdIndex = Integer.valueOf(fdIndexStr);

					Device dependantDevice = deviceDao.get(fdDeviceId);
					PhysicalDevice pd = getPhysicalDeviceObject(dependantDevice);
					FunctionalDevice fd = pd.getFunctionDevice(fdIndex);

					valueObject = fd;
				}
			} else
				valueObject = ci.getValueAsObject();

			map.put(ci.getName(), valueObject);
		}
		try {
			BeanUtils.populate(config, map);
		} catch (IllegalArgumentException e) {
			logger.warn("error setting configuration fields", e);

		}
		return config;
	}
}
