package com.deviceyun.smarthome.api.driver;

/**
 * null means all.
 * 
 * @author jack
 * 
 */
public class SupportedDevice {
	/**
	 * not null-able
	 */
	private String brand;
	/**
	 * not null-able
	 */
	private String product;
	/**
	 * null-able
	 */
	private String model;

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
