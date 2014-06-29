package com.deviceyun.smarthome.device.dao;

import com.deviceyun.smarthome.api.device.DeviceInfo;

public interface DeviceDao {
	DeviceInfo loadDeviceInfo(String id);
}
