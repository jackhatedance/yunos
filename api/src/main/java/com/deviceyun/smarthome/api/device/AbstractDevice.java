package com.deviceyun.smarthome.api.device;

import org.json.JSONObject;

public abstract class AbstractDevice implements FunctionDevice {

	protected JSONObject configure = new JSONObject();



	@Override
	public JSONObject getConfigure() {
		return configure;
	}
}
