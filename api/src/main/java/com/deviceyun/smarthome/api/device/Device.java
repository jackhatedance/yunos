package com.deviceyun.smarthome.api.device;

import org.json.JSONObject;

/**
 * extra entry for vendor specific API
 * 
 * @author jack
 * 
 */
public interface Device {
	/**
	 * 
	 * @param method
	 * @param parameters
	 *            actually, these parameters must implement fromJson/toJson
	 *            interface except basic types.
	 * @return
	 */
	Object operate(String operation, Object[] parameters);

	JSONObject getConfigure();
}
