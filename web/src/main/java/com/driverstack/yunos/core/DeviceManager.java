package com.driverstack.yunos.core;

import com.driverstack.yunos.device.PhysicalDevice;



public interface DeviceManager {

	/**
	 * get functional device object(FDO)
	 * 
	 * @param id
	 * @return
	 */
	PhysicalDevice getPhysicalDeviceObject(String id);
}