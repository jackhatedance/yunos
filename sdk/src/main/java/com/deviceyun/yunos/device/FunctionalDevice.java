package com.deviceyun.yunos.device;

/**
 * it is function device.
 * 
 * @author jack
 * 
 */
public interface FunctionalDevice {
	DeviceApi getApi();

	String getApiVersion();

	Object getConfigure();

	void setConfigure(Object configure);

	void init();
}
