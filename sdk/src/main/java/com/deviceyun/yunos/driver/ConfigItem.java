package com.deviceyun.yunos.driver;

public class ConfigItem {
	private String name;
	private ConfigureItemType type;
	private String functionalDeviceApi;
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

	public String getFunctionalDeviceApi() {
		return functionalDeviceApi;
	}

	public void setFunctionalDeviceApi(String functionalDeviceApi) {
		this.functionalDeviceApi = functionalDeviceApi;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

}
