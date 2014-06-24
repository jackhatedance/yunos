package com.deviceyun.smarthome.api.v1.driver;

public class ConfigItem {
	private String name;
	private ConfigureItemType type;
	private String defaultValue;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public ConfigureItemType getType() {
		return type;
	}

	public void setType(ConfigureItemType type) {
		this.type = type;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

}
