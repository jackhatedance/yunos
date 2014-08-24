package com.deviceyun.yunos.driver.config;

import com.deviceyun.yunos.driver.i18n.I18nString;

/**
 * configure item description
 * 
 * @author jack
 * 
 */
public class ConfigItem {
	private String fieldName;
	private I18nString name;
	private I18nString description;
	private ConfigureItemType type;

	public ConfigItem(String fieldName,I18nString name, I18nString description,
			ConfigureItemType type) {
		this.fieldName = fieldName;
		this.name = name;
		this.description = description;
		this.type = type;
	}

	/**
	 * only available when type is complex type. such as functional device or
	 * enum.
	 * 
	 * if type is functional device, then this is functional device name;
	 * 
	 * if type is enum, then this is enum name;
	 */
	private String subType;

	private I18nString defaultValue;

	
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

	public ConfigureItemType getType() {
		return type;
	}

	public void setType(ConfigureItemType type) {
		this.type = type;
	}

	public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

	public I18nString getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(I18nString defaultValue) {
		this.defaultValue = defaultValue;
	}

}
