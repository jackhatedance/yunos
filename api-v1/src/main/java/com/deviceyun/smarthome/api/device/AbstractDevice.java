package com.deviceyun.smarthome.api.device;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractDevice implements Device {

	protected Map<String,Object> configure = new HashMap<String, Object>();
	
	@Override
	public Object operate(String operation, Object[] parameters) {
		
		throw new UnsupportedOperationException();
	}
	
	@Override
	public Map<String, Object> getConfigure() {		
		return configure;
	}
}
