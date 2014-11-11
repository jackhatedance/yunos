package com.driverstack.yunos.driver.config;

import com.driverstack.yunos.driver.device.FunctionalDevice;

/**
 * why separate this helper from its enum class? because the enum class will be
 * used by both remote applications and server side programs. while this helper
 * depends on FunctionalDevice class which is belonging to driver-api and is not
 * required by remote applications.
 * 
 * @author jack
 * 
 */
public class ConfigurationItemTypeHelper {

	public static ConfigurationItemType getType(Class clazz) {

		if (clazz == String.class)
			return new ConfigurationItemType(
					ConfigurationItemPrimaryType.STRING);
		else if (clazz == Integer.class || clazz == int.class)
			return new ConfigurationItemType(
					ConfigurationItemPrimaryType.INTEGER);
		else if (clazz == Long.class || clazz == long.class)
			return new ConfigurationItemType(ConfigurationItemPrimaryType.LONG);
		else if (clazz == Float.class || clazz == float.class)
			return new ConfigurationItemType(ConfigurationItemPrimaryType.FLOAT);
		else if (clazz.isEnum())
			return new ConfigurationItemType(ConfigurationItemPrimaryType.ENUM);
		else if (FunctionalDevice.class.isAssignableFrom(clazz)) {
			String className = clazz.getCanonicalName();
			return new ConfigurationItemType(
					ConfigurationItemPrimaryType.DEVICE, className);
		} else
			throw new RuntimeException("unknown type:" + clazz.getSimpleName());
	}
}
