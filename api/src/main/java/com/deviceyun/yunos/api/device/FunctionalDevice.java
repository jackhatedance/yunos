package com.deviceyun.yunos.api.device;

import org.json.JSONObject;

/**
 * it is function device.
 * 
 * @author jack
 * 
 */
public interface FunctionalDevice {
	DeviceApi getApi();

	String getApiVersion();

	JSONObject getConfigure();
}
