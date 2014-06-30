package com.deviceyun.smarthome.device;

import java.util.Map;

public class FactoryConfigure {

	/**
	 * not-nullable
	 */
	String vendor;
	
	/**
	 * not-nullable
	 */
	String productName;
	/**
	 * not-nullable
	 */
	String model;
	/**
	 * nullable
	 */
	String revision;
	
	/**
	 * factory configure
	 */
	Map<String, Object> configure;
}
