package net.abstractfactory.yunos.service;

import java.util.List;

import net.abstractfactory.yunos.domain.Device;
import net.abstractfactory.yunos.domain.DeviceClass;
import net.abstractfactory.yunos.domain.Driver;

public interface DeviceService {

	List<Device> listByUserId(String userId, DeviceClass deviceClass);

	String save(Device device);

	void update(Device device);

	void remove(String deviceId);

	/**
	 * init configuration items from driver configuration definition, pre-fill
	 * with factory values
	 * 
	 * @param driver
	 */
	List<net.abstractfactory.yunos.domain.ConfigurationItem> createConfiguration(
			Device device, Driver driver);
}
