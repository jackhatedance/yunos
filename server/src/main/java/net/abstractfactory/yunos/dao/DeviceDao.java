package net.abstractfactory.yunos.dao;

import java.util.List;

import net.abstractfactory.yunos.domain.Device;
import net.abstractfactory.yunos.domain.DeviceClass;

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
