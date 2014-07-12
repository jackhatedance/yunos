package com.deviceyun.yunos.api.device;

/**
 * we can define API for a certain type of product. usually we define and
 * publish for generic products.
 * 
 * for example, a device API is defined for a certain product, but not for a
 * specific model.
 * 
 * @author jackding
 * 
 */
public class DeviceApi {

	private String brand;
	private String category;
	private String product;

	public DeviceApi(String brand, String category, String product) {
		this.brand = brand;
		this.category = category;
		this.product = product;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

}
