package com.deviceyun.yunos.api.device;


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
}
