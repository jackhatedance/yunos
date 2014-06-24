package com.deviceyun.smarthome.api.v1.device;

import java.util.Map;

/**
 * extra entry for vendor specific API
 * 
 * @author jack
 * 
 */
public interface Device {
	Object[] invoke(String method, Object[] parameters);
	
	Map<String,Object> getConfigure();
}
