package com.deviceyun.yunos.api.driver;

import java.util.List;

import com.deviceyun.yunos.api.device.DeviceInfo;
import com.deviceyun.yunos.api.device.FunctionalDevice;
import com.deviceyun.yunos.api.device.Model;

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
	 * the version of SDK from deviceyun
	 * 
	 * @return
	 */
	String getSdkVersion();

	List<Model> getSupportedModels();

	List<ConfigItem> getConfigItems();

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
	 * create functional device object.
	 * 
	 * @param info
	 * @return
	 */
	FunctionalDevice createDevice(DeviceInfo info);
}
