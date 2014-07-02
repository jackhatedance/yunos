package com.deviceyun.smarthome.api.device;

import org.json.JSONObject;

/**
 * it is function device.
 * 
 * @author jack
 * 
 */
public interface FunctionDevice {

	String getVersion();

	/**
	 * 
	 * @param method
	 * @param parameters
	 *            actually, these parameters must implement fromJson/toJson
	 *            interface except basic types.
	 * @return
	 */
	Object invoke(String method, Object[] parameters);

	JSONObject getConfigure();
}
