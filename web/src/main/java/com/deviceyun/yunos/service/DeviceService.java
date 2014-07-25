package com.deviceyun.yunos.service;

import java.util.List;

import com.deviceyun.yunos.domain.Device;

public interface DeviceService {

	List<Device> listByUserId(String userId);

	String saveDevice(Device device);

	void remove(String deviceId);
}
