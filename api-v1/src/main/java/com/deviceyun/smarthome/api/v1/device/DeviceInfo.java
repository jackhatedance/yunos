package com.deviceyun.smarthome.api.v1.device;

public class DeviceInfo {
	private String uuid;
	private String vendor;
	private String product;
	private String model;
	private String revision;
	private String mfgSerialNumber;
	/**
	 * only compatible with generic device model. 
	 */
	private String compatibleDevice;
	
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
