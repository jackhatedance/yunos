package net.abstractfactory.yunos.driver.device;

public class Model {

	/**
	 * not-nullable
	 */
	private String vendor;

	/**
	 * not-nullable
	 */
	private String model;

	public Model(String vendor, String model) {
		this.vendor = vendor;
		this.model = model;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

}
