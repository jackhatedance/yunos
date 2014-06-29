package com.deviceyun.smarthome.device;

import java.util.Map;

import com.deviceyun.smarthome.api.device.Device;
import com.deviceyun.smarthome.api.device.DeviceInfo;
import com.deviceyun.smarthome.device.dao.DeviceDao;
import com.deviceyun.smarthome.driver.Driver;
import com.deviceyun.smarthome.driver.DriverManager;

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
	Map<String, Device> devices = null;

	public Device getDevice(String id) {
		
		if(devices.containsKey(id))
			return devices.get(id);
		else
		{
			Device device = loadDevice(id);
			return device;
		}
		
	}

	private Device loadDevice(String id) {
		DeviceInfo info = deviceDao.loadDeviceInfo(id);
		
		Driver driver = driverManager.findDriver(info);
		
		Device device =driver.createDevice(info);
		
		return device;
	}

	private void saveDevice(String id) {

	}

	public void operateDevice(String apiKey, String deviceId, String operation,
			Map<String, Object> parameters) {

	}
}
