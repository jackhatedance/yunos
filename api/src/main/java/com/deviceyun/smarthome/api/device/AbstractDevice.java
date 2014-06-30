package com.deviceyun.smarthome.api.device;

import org.json.JSONObject;

public abstract class AbstractDevice implements Device {

	protected JSONObject configure = new JSONObject();

	@Override
	public String getVersion() {
		return null;
	}

	@Override
	public Object operate(String operation, Object[] parameters) {

		throw new UnsupportedOperationException();
	}

	@Override
	public JSONObject getConfigure() {
		return configure;
	}
}
