package com.deviceyun.smarthome.api.device;

import org.json.JSONObject;

public abstract class AbstractDevice implements FunctionDevice {

	protected JSONObject configure = new JSONObject();

	@Override
	public Object invoke(String method, Object[] parameters) {

		throw new UnsupportedOperationException();
	}

	@Override
	public JSONObject getConfigure() {
		return configure;
	}
}
