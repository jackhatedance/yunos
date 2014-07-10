package com.deviceyun.smarthome.api.device;

import org.json.JSONObject;

/**
 * it is function device.
 * 
 * @author jack
 * 
 */
public interface FunctionDevice {
	Api getApi();

	/**
	 * it is API version.
	 * 
	 * @return
	 */
	String getApiVersion();

	

	JSONObject getConfigure();
}
