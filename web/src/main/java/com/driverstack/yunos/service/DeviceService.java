package com.driverstack.yunos.service;

import java.util.List;

import com.driverstack.yunos.domain.Device;
import com.driverstack.yunos.domain.Driver;

public interface DeviceService {

	List<Device> listByUserId(String userId);

	String save(Device device);

	void update(Device device);

	void remove(String deviceId);

	/**
	 * init configuration items from driver configuration definition, pre-fill
	 * with factory values
	 * 
	 * @param driver
	 */
	List<com.driverstack.yunos.domain.ConfigurationItem> createConfiguration(Device device, Driver driver);
}
