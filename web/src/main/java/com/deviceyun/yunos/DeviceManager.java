package com.deviceyun.yunos;

import com.deviceyun.yunos.dao.mybatisMapper.DeviceMapper;

public interface DeviceManager {
	void setDriverManager(DriverManagerImpl driverManager);

	void setDeviceMapper(DeviceMapper deviceMapper);
}