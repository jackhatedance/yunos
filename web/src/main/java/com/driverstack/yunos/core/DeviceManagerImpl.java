package com.driverstack.yunos.core;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.RuntimeErrorException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.driverstack.yunos.ExecutionEnvironment;
import com.driverstack.yunos.core.device.DeviceStatusChangeListener;
import com.driverstack.yunos.core.device.FunctionalDeviceProxy;
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
	private DriverObjectFactory driverObjectFactory;

	@Autowired
	private ExecutionEnvironment executionEnvironment;

	// memory cache
	Map<String, PhysicalDevice> devices = new HashMap<String, PhysicalDevice>();

	Map<String, List<DeviceStatusChangeListener>> deviceDriverChangeListenerMap = new HashMap<String, List<DeviceStatusChangeListener>>();

	public void setDriverManager(DriverManagerImpl driverManager) {
		this.driverManager = driverManager;
	}

	public void setDeviceDao(DeviceDao deviceDao) {
		this.deviceDao = deviceDao;
	}

	public ExecutionEnvironment getExecutionEnvironment() {
		return executionEnvironment;
	}

	public void setExecutionEnvironment(
			ExecutionEnvironment executionEnvironment) {
		this.executionEnvironment = executionEnvironment;
	}

	@Override
	public synchronized PhysicalDevice getPhysicalDeviceObject(String deviceId) {

		Device domainDevice = deviceDao.get(deviceId);

		if (devices.containsKey(deviceId))
			return devices.get(deviceId);
		else {
			if (domainDevice.getDriver() != null) {
				PhysicalDevice device = loadDevice(domainDevice);
				devices.put(deviceId, device);

				return device;
			} else
				throw new RuntimeException("Driver not set");
		}

	}

	private void addDriverChangeListener(String deviceId,
			DeviceStatusChangeListener listener) {
		List<DeviceStatusChangeListener> listeners = deviceDriverChangeListenerMap
				.get(deviceId);
		if (listeners == null) {
			listeners = new ArrayList<DeviceStatusChangeListener>();
			deviceDriverChangeListenerMap.put(deviceId, listeners);
		}

		listeners.add(listener);
	}

	private void removeDriverChangeListener(String deviceId,
			DeviceStatusChangeListener listener) {
		List<DeviceStatusChangeListener> listeners = deviceDriverChangeListenerMap
				.get(deviceId);
		if (listeners != null) {
			listeners.remove(listener);

		}
	}

	/**
	 * notify subscriber
	 * 
	 * @param device
	 */
	private void notifyUnloadDeviceEvent(String deviceId) {
		List<DeviceStatusChangeListener> listeners = deviceDriverChangeListenerMap
				.get(deviceId);
		if (listeners != null) {
			for (DeviceStatusChangeListener listener : listeners) {
				listener.onDriverUnload();
			}
		}
	}

	private void unloadDevice(Device domainDevice) {
		String deviceId = domainDevice.getId();
		if (devices.containsKey(deviceId)) {
			PhysicalDevice physicalDevice = devices.remove(deviceId);

			notifyUnloadDeviceEvent(deviceId);

			// TODO unregister driver change listeners.
			Object config = physicalDevice.getConfigure();
			for (ConfigurationItem ci : domainDevice
					.getUserConfigurationItems().values()) {

				if (ci.getType() == ConfigurationItemPrimaryType.DEVICE) {

					try {
						Object fd = PropertyUtils.getProperty(config,
								ci.getName());

						//keep backward compatiblity
						if (fd instanceof DeviceStatusChangeListener)
							removeDriverChangeListener(deviceId,
									(DeviceStatusChangeListener) fd);

					} catch (Exception e) {

						throw new RuntimeException(
								"read configuration property error, name:"
										+ ci.getName(), e);
					}

				}

			}

			physicalDevice.destroy();
		} else
			throw new RuntimeException(
					"failed to unload, device is not loaded, deviceId:"
							+ deviceId);
	}

	private PhysicalDevice loadDevice(Device domainDevice) {

		Driver runtimeDriver = driverManager.loadDriver(domainDevice);
		try {
			Object config = createConfig(domainDevice,
					runtimeDriver.getConfigureClass());

			DeviceInfo devInfo = domainDevice.getInfo();
			devInfo.setConfigure(config);

			PhysicalDevice physicalDevice = runtimeDriver.createDevice(
					executionEnvironment, devInfo);

			return physicalDevice;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	private Object createConfig(Device domainDevice, Class configClass)
			throws InstantiationException, IllegalAccessException,
			InvocationTargetException, ClassNotFoundException {

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

					String interfaceClassName = ci.getTypeParameter();

					if (interfaceClassName == null) {
						// keep compatible with old way.
						PhysicalDevice pd = getPhysicalDeviceObject(fdDeviceId);
						FunctionalDevice fd = pd.getFunctionDevice(fdIndex);

						valueObject = fd;
					} else {
						Object fdProxy = driverObjectFactory
								.createFunctionalDeviceProxy(
										interfaceClassName, this, fdDeviceId,
										fdIndex);

						addDriverChangeListener(fdDeviceId,
								(DeviceStatusChangeListener) fdProxy);

						valueObject = fdProxy;
					}
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

	private boolean isLoaded(String deviceId) {
		return devices.containsKey(deviceId);

	}

	@Override
	public synchronized PhysicalDevice reloadDriver(Device domainDevice) {
		if (isLoaded(domainDevice.getId()))
			unloadDevice(domainDevice);

		return loadDevice(domainDevice);

	}
}
