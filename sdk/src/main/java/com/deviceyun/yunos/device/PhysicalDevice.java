package com.deviceyun.yunos.device;

import java.util.List;

/**
 * it is physical device.
 * 
 * @author jack
 * 
 */
public interface PhysicalDevice {

	Object getConfigure();

	void setConfigure(Object configure);

	void init();

	
	
	List<DeviceApi> querySupportedDeviceApis();
	
	List<String> querySupportedDeviceApiVersions(DeviceApi deviceApi);
	
	/**
	 * after user selected a functional device API and version, the consumer device may need it.
	 * 
	 * @param deviceApi
	 * @param version
	 * @return
	 */
	String getFunctionalDeviceName(DeviceApi deviceApi, String version);
	
	FunctionalDevice getFunctionalDevice(String name);

	/**
	 * test if a API is supported
	 * 
	 * @param deviceApi
	 * @return
	 */
	boolean support(DeviceApi deviceApi, String version);
}
