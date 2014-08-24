package com.deviceyun.yunos.driver.config;

import com.deviceyun.yunos.device.FunctionalDevice;

public enum ConfigureItemType {

	STRING, INTEGER, FLOAT, ENUM, FUNCTIONAL_DEVICE;

	public static ConfigureItemType getType(Class clazz) {
		if (clazz==String.class)
			return STRING;
		else if (clazz==Integer.class)
			return INTEGER;
		else if (clazz==Float.class)
			return FLOAT;
		else if (clazz.isEnum())
			return ENUM;
		else if (clazz==FunctionalDevice.class)
			return FUNCTIONAL_DEVICE;
		else
			return null;
	}
}
