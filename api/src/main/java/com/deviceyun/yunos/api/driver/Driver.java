package com.deviceyun.yunos.api.driver;

import java.util.List;

import com.deviceyun.yunos.api.device.DeviceApi;
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

	String getSdkVersion();

	List<Model> getSupportedModels();

	List<ConfigItem> getConfigItems();

	DeviceApi getFunctionalDeviceApi();

	String getFunctionalDeviceApiVersion();

	/**
	 * install driver for the device.
	 * 
	 * some initialization work may need be done here.
	 */
	void install(DeviceInfo info);

	/**
	 * create functional device object.
	 * 
	 * @param info
	 * @return
	 */
	FunctionalDevice createDevice(DeviceInfo info);
}
