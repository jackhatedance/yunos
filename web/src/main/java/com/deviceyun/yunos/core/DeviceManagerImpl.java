package com.deviceyun.yunos.core;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.deviceyun.yunos.dao.DeviceDao;
import com.deviceyun.yunos.device.FunctionalDevice;
import com.deviceyun.yunos.device.PhysicalDevice;
import com.deviceyun.yunos.domain.Device;
import com.deviceyun.yunos.driver.Driver;

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
@Component
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

	public PhysicalDevice getPhysicalDeviceObject(String id) {

		if (devices.containsKey(id))
			return devices.get(id);
		else {
			PhysicalDevice device = loadDevice(id);
			return device;
		}

	}

	private PhysicalDevice loadDevice(String id) {
		Device deviceEntity = deviceDao.get(id);
		// System.out.println("device name:"+deviceEntity.getName());

		Driver driverObject = driverManager.loadDriver(deviceEntity);

		PhysicalDevice physicalDevice = driverObject
				.createDevice(deviceEntity.getInfo());

		return physicalDevice;
	}
}
