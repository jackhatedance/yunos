package com.driverstack.yunos.core;

import com.driverstack.yunos.domain.Device;
import com.driverstack.yunos.driver.device.PhysicalDevice;



public interface DeviceManager {

	/**
	 * get functional device object(FDO)
	 * 
	 * @param id
	 * @return
	 */
	PhysicalDevice getPhysicalDeviceObject(Device domainDevice);
}