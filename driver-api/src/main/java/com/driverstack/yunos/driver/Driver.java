package com.driverstack.yunos.driver;

import java.util.List;

import com.driverstack.yunos.driver.device.DeviceInfo;
import com.driverstack.yunos.driver.device.FunctionalDevice;
import com.driverstack.yunos.driver.device.Model;
import com.driverstack.yunos.driver.device.PhysicalDevice;

/**
 * a driver submit by developers.
 * 
 * @author jackding
 * 
 */
public interface Driver {
	/**
	 * be unique, usually is a domain name, such as com.example
	 * 
	 * @return
	 */

	String getOrganizationId();

	/**
	 * a unique name within the organization.
	 * 
	 * @return
	 */
	String getDriverId();

	/**
	 * version of the driver
	 * 
	 * @return
	 */
	String getVersion();

	/**
	 * the version of SDK 
	 * 
	 * @return
	 */
	String getSdkVersion();

	List<Model> getSupportedModels();

	/**
	 * the configure class is used to collect configure items by its annotations
	 * @return
	 */
	Class getConfigureClass();

	/**
	 * install driver for the device.
	 * 
	 * some initialization work may need be done here.
	 */
	void install(DeviceInfo info);

	/**
	 * uninstall driver
	 * 
	 * @param info
	 */
	void uninstall(DeviceInfo info);

	/**
	 * create device object.
	 * 
	 * @param info
	 * @return
	 */
	PhysicalDevice createDevice(DeviceInfo info);
}
