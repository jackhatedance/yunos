package com.deviceyun.smarthome.api.device;

import java.util.Map;

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
	 * @param parameters actually, these parameters must implement fromJson/toJson interface except basic types.
	 * @return
	 */
	Object[] invoke(String method, Object[] parameters);
	
	Map<String,Object> getConfigure();
}
