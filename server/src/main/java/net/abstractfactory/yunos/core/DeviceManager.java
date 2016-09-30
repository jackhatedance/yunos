package net.abstractfactory.yunos.core;

import net.abstractfactory.yunos.domain.Device;
import net.abstractfactory.yunos.driver.device.PhysicalDevice;



public interface DeviceManager {

	/**
	 * get functional device object(FDO)
	 * 
	 * @param id
	 * @return
	 */
	PhysicalDevice getPhysicalDeviceObject(String deviceId);
	/**
	 * unload old driver, load new driver.
	 * @param domainDevice
	 * @return
	 */
	PhysicalDevice reloadDriver(Device domainDevice);
	
	
}