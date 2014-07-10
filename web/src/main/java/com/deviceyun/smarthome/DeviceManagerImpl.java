package com.deviceyun.smarthome;

import java.util.Map;

import com.deviceyun.smarthome.api.device.FunctionDevice;
import com.deviceyun.smarthome.api.driver.Driver;
import com.deviceyun.smarthome.dao.mybatisMapper.DeviceMapper;
import com.deviceyun.smarthome.domain.Device;

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
public class DeviceManagerImpl implements DeviceManager {
	private DeviceMapper deviceMapper;

	private DriverManager driverManager;

	// memory cache
	Map<String, FunctionDevice> devices = null;

	public void setDriverManager(DriverManagerImpl driverManager) {
		this.driverManager = driverManager;
	}

	public void setDeviceMapper(DeviceMapper deviceMapper) {
		this.deviceMapper = deviceMapper;
	}

	public FunctionDevice getDevice(String id) {

		if (devices.containsKey(id))
			return devices.get(id);
		else {
			FunctionDevice device = loadDevice(id);
			return device;
		}

	}

	private FunctionDevice loadDevice(String id) {
		Device device = deviceMapper.getDevice(id);

		Driver driver = driverManager.findDriver(device.getModel()
				.getVO());

		FunctionDevice functionDevice = driver.createDevice(device.getInfo());

		return functionDevice;
	}

	private void saveDevice(String id) {

	}

}
