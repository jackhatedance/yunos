package com.deviceyun.smarthome.api.v1.device;

import java.util.Map;

public class AbstractDevice implements Device {

	
	@Override
	public Object[] invoke(String method, Object[] parameters) {
		return null;
	}
	
	@Override
	public Map<String, Object> getConfigure() {		
		return null;
	}
}
