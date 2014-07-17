package com.deviceyun.yunos.core;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.deviceyun.yunos.api.device.FunctionalDevice;
import com.deviceyun.yunos.api.driver.Driver;
import com.deviceyun.yunos.dao.DeviceDao;
import com.deviceyun.yunos.domain.Device;

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
	Map<String, FunctionalDevice> devices = new HashMap<String, FunctionalDevice>();

	public void setDriverManager(DriverManagerImpl driverManager) {
		this.driverManager = driverManager;
	}

	public void setDeviceDao(DeviceDao deviceDao) {
		this.deviceDao = deviceDao;
	}

	public FunctionalDevice getFunctionDeviceObject(String id) {

		if (devices.containsKey(id))
			return devices.get(id);
		else {
			FunctionalDevice device = loadDevice(id);
			return device;
		}

	}

	private FunctionalDevice loadDevice(String id) {
		Device deviceEntity = deviceDao.get(id);
		System.out.println("device name:"+deviceEntity.getName());

		Driver driverObject = driverManager.loadDriver(deviceEntity);

		FunctionalDevice functionDevice = driverObject
				.createDevice(deviceEntity.getInfo());

		return functionDevice;
	}

}
