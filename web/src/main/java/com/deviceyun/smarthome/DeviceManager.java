package com.deviceyun.smarthome;

import java.util.Map;

import com.deviceyun.smarthome.api.device.FunctionDevice;
import com.deviceyun.smarthome.api.driver.Driver;
import com.deviceyun.smarthome.dao.DeviceDao;
import com.deviceyun.smarthome.dao.entity.DeviceEntity;

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
public class DeviceManager {
	DriverManager driverManager = null;
	DeviceDao deviceDao = null;

	// memory cache
	Map<String, FunctionDevice> devices = null;

	public FunctionDevice getDevice(String id) {

		if (devices.containsKey(id))
			return devices.get(id);
		else {
			FunctionDevice device = loadDevice(id);
			return device;
		}

	}

	private FunctionDevice loadDevice(String id) {
		DeviceEntity deviceEntity = deviceDao.loadDeviceInfo(id);

		Driver driver = driverManager.findDriver(deviceEntity.getModel()
				.getVO());

		FunctionDevice device = driver.createDevice(deviceEntity.getInfo());

		return device;
	}

	private void saveDevice(String id) {

	}

}
