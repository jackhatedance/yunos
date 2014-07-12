package com.deviceyun.yunos.api.device;

import org.json.JSONObject;

public abstract class AbstractDevice implements FunctionalDevice {

	protected JSONObject configure = new JSONObject();



	@Override
	public JSONObject getConfigure() {
		return configure;
	}
}
