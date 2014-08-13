package com.deviceyun.yunos.remote.vo;

import java.io.Serializable;

/**
 * 
 * 
 * @author jack
 * 
 */

public class Model implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1953981223040246980L;

	private String brand;

	private String product;

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
