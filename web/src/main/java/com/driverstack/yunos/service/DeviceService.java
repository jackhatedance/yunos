package com.driverstack.yunos.service;

import java.util.List;

import com.driverstack.yunos.domain.Device;

public interface DeviceService {

	List<Device> listByUserId(String userId);

	String save(Device device);

	void update(Device device);
	
	void remove(String deviceId);
}
