package com.deviceyun.yunos.dao;

import java.util.List;

import com.deviceyun.yunos.domain.Device;

public interface DeviceDao {

	/**
	 * get Device entity by ID
	 * 
	 * @param id
	 * @return
	 */
	Device get(String id);

	List<Device> listByUser(String userId);
}
