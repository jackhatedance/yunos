package com.deviceyun.smarthome;

import com.deviceyun.smarthome.dao.mybatisMapper.DeviceMapper;

public interface DeviceManager {
	void setDriverManager(DriverManagerImpl driverManager);

	void setDeviceMapper(DeviceMapper deviceMapper);
}