package com.deviceyun.yunos.core;

import com.deviceyun.yunos.device.PhysicalDevice;



public interface DeviceManager {

	/**
	 * get functional device object(FDO)
	 * 
	 * @param id
	 * @return
	 */
	PhysicalDevice getPhysicalDeviceObject(String id);
}