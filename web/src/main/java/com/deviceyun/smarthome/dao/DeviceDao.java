package com.deviceyun.smarthome.dao;

import com.deviceyun.smarthome.dao.entity.DeviceEntity;

public interface DeviceDao {
	DeviceEntity loadDeviceInfo(String id);
}
