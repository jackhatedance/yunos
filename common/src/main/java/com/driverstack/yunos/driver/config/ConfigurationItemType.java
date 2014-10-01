package com.driverstack.yunos.driver.config;

public class ConfigurationItemType {
	private ConfigurationItemPrimaryType type;
	private String parameter;

	public ConfigurationItemType() {
		// keep it for JSON tools.
	}

	public ConfigurationItemType(ConfigurationItemPrimaryType type) {
		this.type = type;

	}

	public ConfigurationItemType(ConfigurationItemPrimaryType type,
			String parameter) {
		this.type = type;
		this.parameter = parameter;

	}

	public ConfigurationItemPrimaryType getType() {
		return type;
	}

	public void setType(ConfigurationItemPrimaryType type) {
		this.type = type;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

}
