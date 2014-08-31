package com.driverstack.yunos.driver.config;

import javax.management.RuntimeErrorException;

import com.driverstack.yunos.device.FunctionalDevice;

public enum ConfigurationItemType {

	STRING, INTEGER, FLOAT, ENUM, FUNCTIONAL_DEVICE;

	public static ConfigurationItemType getType(Class clazz) {
		if (clazz==String.class)
			return STRING;
		else if (clazz==Integer.class ||clazz==int.class)
			return INTEGER;
		
		else if (clazz==Float.class||clazz==float.class)
			return FLOAT;
		else if (clazz.isEnum())
			return ENUM;
		else if (clazz==FunctionalDevice.class)
			return FUNCTIONAL_DEVICE;
		else
			throw new RuntimeException("unknown type:"+clazz.getTypeName());
	}
}
