package com.deviceyun.yunos.core;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.deviceyun.yunos.api.device.FunctionalDevice;
import com.deviceyun.yunos.api.driver.Driver;
import com.deviceyun.yunos.dao.mybatisMapper.DeviceMapper;
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
	private DeviceMapper deviceMapper;
	@Autowired
	private DriverManager driverManager;

	// memory cache
	Map<String, FunctionalDevice> devices = null;

	public void setDriverManager(DriverManagerImpl driverManager) {
		this.driverManager = driverManager;
	}

	public void setDeviceMapper(DeviceMapper deviceMapper) {
		this.deviceMapper = deviceMapper;
	}

	public FunctionalDevice getDevice(String id) {

		if (devices.containsKey(id))
			return devices.get(id);
		else {
			FunctionalDevice device = loadDevice(id);
			return device;
		}

	}

	private FunctionalDevice loadDevice(String id) {
		Device device = deviceMapper.getDevice(id);

		Driver driver = driverManager.findDriver(device.getModel().getVO());

		FunctionalDevice functionDevice = driver.createDevice(device.getInfo());

		return functionDevice;
	}

	private void saveDevice(String id) {

	}

}
