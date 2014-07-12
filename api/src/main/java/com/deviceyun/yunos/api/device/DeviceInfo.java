package com.deviceyun.yunos.api.device;

import org.json.JSONObject;

/**
 * device descriptions
 * 
 * @author jack
 * 
 */
public class DeviceInfo {

	private String id;
	private Model model;
	/**
	 * configures combined by device configure and model configure. device
	 * configure has higher priority.
	 */
	private JSONObject configure;

	public DeviceInfo(String id, Model model, JSONObject configure) {
		this.id = id;
		this.model = model;
		this.configure = configure;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public JSONObject getConfigure() {
		return configure;
	}

	public void setConfigure(JSONObject configure) {
		this.configure = configure;
	}

}
