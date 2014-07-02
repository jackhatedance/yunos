package com.deviceyun.smarthome.dao.entity;

import org.json.JSONObject;

/**
 * it is a entity.
 * 
 * @author jack
 * 
 */
public class ModelEntity {
	private String id;

	/**
	 * not-nullable
	 */
	private String brand;

	/**
	 * not-nullable
	 */
	private String product;
	/**
	 * not-nullable
	 */
	private String model;

	private ModelEntity[] compatibleModels;

	/**
	 * factory configure
	 */
	private JSONObject configure;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public JSONObject getConfigure() {
		return configure;
	}

	public void setConfigure(JSONObject configure) {
		this.configure = configure;
	}

	public com.deviceyun.smarthome.api.device.Model getVO() {
		com.deviceyun.smarthome.api.device.Model m = new com.deviceyun.smarthome.api.device.Model(
				brand, product, model);
		return m;
	}
}
