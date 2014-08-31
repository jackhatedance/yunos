package com.driverstack.yunos.device;

import com.google.gson.JsonObject;

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
	private JsonObject configure;

	public DeviceInfo(String id, Model model, JsonObject configure) {
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

	public JsonObject getConfigure() {
		return configure;
	}

	public void setConfigure(JsonObject configure) {
		this.configure = configure;
	}

}
