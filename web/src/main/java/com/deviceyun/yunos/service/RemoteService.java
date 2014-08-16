package com.deviceyun.yunos.service;

import java.util.List;
import java.util.Map;

import com.deviceyun.yunos.remote.vo.Brand;
import com.deviceyun.yunos.remote.vo.Device;

public interface RemoteService {
	/**
	 * login to get token, which will be used for later calling API
	 * 
	 * @param userId
	 * @param password
	 * @return token
	 */
	String login(String userId, String password);

	/**
	 * list devices of a user.
	 * 
	 * @param userId
	 * @return
	 */
	List<Device> listDevice(String userId);

	Device getDevice(String deviceId);

	/**
	 * user add a new device. it returns a device ID;
	 * 
	 * @param userId
	 * @param device
	 */
	void addDevice(String userId, Device device);

	/**
	 * can not delete others device
	 * 
	 * @param deviceId
	 */
	void removeDevice(String deviceId);

	/**
	 * load device-specific configure
	 * 
	 * @param deviceId
	 * @return
	 */
	Object loadDeviceConfig(String deviceId);

	/**
	 * update user configure
	 * 
	 * @param deviceId
	 * @param config
	 */
	void updateDeviceConfig(String deviceId, String config);

	/**
	 * operate a device. the functional device API is varying.
	 * 
	 * @param deviceId
	 * @param functionalDeviceIndex
	 * @param operationName
	 * @param parameters
	 * @return
	 */
	Object operateDevice(String deviceId, int functionalDeviceIndex,
			String operationName, Map<String, String> parameters);

	/**
	 * 
	 * @param locale such as en_US
	 * @return
	 */
	List<Brand> getAllBrands(String locale);
}