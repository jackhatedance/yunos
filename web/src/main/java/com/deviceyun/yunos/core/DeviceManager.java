package com.deviceyun.yunos.core;

import com.deviceyun.yunos.api.device.FunctionalDevice;
import com.deviceyun.yunos.dao.mybatisMapper.DeviceMapper;

public interface DeviceManager {
	void setDriverManager(DriverManagerImpl driverManager);

	void setDeviceMapper(DeviceMapper deviceMapper);

	/**
	 * get functional device object
	 * 
	 * @param id
	 * @return
	 */
	FunctionalDevice getDevice(String id);
}