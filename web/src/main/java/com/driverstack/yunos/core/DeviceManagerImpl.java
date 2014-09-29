package com.driverstack.yunos.core;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.driverstack.yunos.dao.DeviceDao;
import com.driverstack.yunos.domain.Device;
import com.driverstack.yunos.driver.Driver;
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
	@Autowired
	private DeviceDao deviceDao;
	@Autowired
	private DriverManager driverManager;

	// memory cache
	Map<String, PhysicalDevice> devices = new HashMap<String, PhysicalDevice>();

	public void setDriverManager(DriverManagerImpl driverManager) {
		this.driverManager = driverManager;
	}

	public void setDeviceDao(DeviceDao deviceDao) {
		this.deviceDao = deviceDao;
	}

	public PhysicalDevice getPhysicalDeviceObject(Device domainDevice) {

		String deviceId= domainDevice.getId();
		if (devices.containsKey(deviceId))
			return devices.get(deviceId);
		else {			
			PhysicalDevice device = loadDevice(domainDevice);
			return device;
		}

	}

	private PhysicalDevice loadDevice(Device domainDevice) {

		Driver runtimeDriver = driverManager.loadDriver(domainDevice);

		PhysicalDevice physicalDevice = runtimeDriver.createDevice(domainDevice
				.getInfo());

		return physicalDevice;

	}
}
