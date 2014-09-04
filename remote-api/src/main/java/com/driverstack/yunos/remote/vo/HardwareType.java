package com.driverstack.yunos.remote.vo;

import java.io.Serializable;

/**
 * 
 * 
 * @author jack
 * 
 */

public class HardwareType implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1953981223040246980L;

	private String vendor;

	private String model;

	
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
