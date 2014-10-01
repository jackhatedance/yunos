package com.driverstack.yunos.remote.vo;

import com.driverstack.yunos.driver.config.ConfigurationItemType;

public class ConfigurationItem {

	private String name;
	private ConfigurationItemType type;
	private String value;

	public ConfigurationItem() {
		// keep this constructor for JSON de-serializer.
	}

	public ConfigurationItem(String name, ConfigurationItemType type,
			String value) {
		this.name = name;
		this.type = type;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ConfigurationItemType getType() {
		return type;
	}

	public void setType(ConfigurationItemType type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
