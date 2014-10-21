package com.driverstack.yunos.dao;

import java.util.List;

import com.driverstack.yunos.domain.Device;
import com.driverstack.yunos.domain.DeviceClass;

public interface DeviceDao {

	/**
	 * get Device entity by ID
	 * 
	 * @param id
	 * @return
	 */
	Device get(String id);

	List<Device> listByUser(String userId, DeviceClass deviceClass);
}
