package net.abstractfactory.yunos.driver.config;

import net.abstractfactory.yunos.driver.config.ConfigurationItemType;
import net.abstractfactory.yunos.driver.i18n.I18nString;

/**
 * configure item description
 * 
 * @author jack
 * 
 */
public class ConfigurationItem {
	private String fieldName;
	private I18nString name;
	private I18nString description;
	private ConfigurationItemType type;
	private String defaultValue;
	private int order;

	public ConfigurationItem(String fieldName, I18nString name,
			I18nString description, ConfigurationItemType type,
			String defaultValue, int order) {
		this.fieldName = fieldName;
		this.name = name;
		this.description = description;
		this.type = type;
		this.defaultValue = defaultValue;
		this.order = order;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public I18nString getName() {
		return name;
	}

	public void setName(I18nString name) {
		this.name = name;
	}

	public I18nString getDescription() {
		return description;
	}

	public void setDescription(I18nString description) {
		this.description = description;
	}

	public ConfigurationItemType getType() {
		return type;
	}

	public void setType(ConfigurationItemType type) {
		this.type = type;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

}
