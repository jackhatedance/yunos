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
	private String vendor;
	/**
	 * not null-able
	 */
	private String product;
	/**
	 * null-able
	 */
	private String model;
	/**
	 * some vendors don't have rev for some products. .
	 * 
	 * TODO support wild card values: * all, A?? ,etc. *
	 * 
	 */
	private String revision;

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
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

	public String getRevision() {
		return revision;
	}

	public void setRevision(String revision) {
		this.revision = revision;
	}

}
