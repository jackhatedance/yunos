package com.deviceyun.yunos.device;

public class Model {

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

	public Model(String brand, String product, String model) {
		this.brand = brand;
		this.product = product;
		this.model = model;
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

}
