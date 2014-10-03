package com.driverstack.yunos.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import com.driverstack.yunos.driver.config.ConfigurationItemPrimaryType;
import com.driverstack.yunos.driver.config.ConfigurationItemType;

/**
 * configuration item (name and value) for device, model default and vendor
 * default.
 * 
 * @author jack
 * 
 */
@Entity
public class ConfigurationItem {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	@Column
	private String name;

	@Enumerated(EnumType.STRING)
	@Column
	private ConfigurationItemPrimaryType type;
	@Column
	private String typeParameter;
	@Column
	private String value;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ConfigurationItemPrimaryType getType() {
		return type;
	}

	public void setType(ConfigurationItemPrimaryType type) {
		this.type = type;
	}

	public String getTypeParameter() {
		return typeParameter;
	}

	public void setTypeParameter(String typeParameter) {
		this.typeParameter = typeParameter;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public ConfigurationItem() {
	}

	public ConfigurationItem(String name, ConfigurationItemPrimaryType type,
			String value) {
		this.name = name;
		this.type = type;
		this.value = value;
	}

	/**
	 * copy constructor
	 * 
	 * @param remoteConfigurationItem
	 */
	public ConfigurationItem(ConfigurationItem src) {
		this.name = src.getName();
		this.type = src.getType();
		this.value = src.getValue();
	}

	public ConfigurationItem(
			com.driverstack.yunos.remote.vo.ConfigurationItem remoteConfigurationItem) {
		this.name = remoteConfigurationItem.getName();
		this.type = remoteConfigurationItem.getType().getType();
		this.typeParameter = remoteConfigurationItem.getType().getParameter();
		this.value = remoteConfigurationItem.getValue();
	}

	public com.driverstack.yunos.remote.vo.ConfigurationItem toRemoteVO() {
		com.driverstack.yunos.remote.vo.ConfigurationItem remoteItem = new com.driverstack.yunos.remote.vo.ConfigurationItem(
				name, new ConfigurationItemType(type, typeParameter), value);

		return remoteItem;
	}

	public Object getValueAsObject() {
		return type.fromString(value);
	}
}
