package com.driverstack.yunos.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

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
	@Column
	private String type;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public ConfigurationItem() {
	}

	public ConfigurationItem(String name, String type, String value) {
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
		this.type = remoteConfigurationItem.getType();
		this.value = remoteConfigurationItem.getValue();
	}

	public com.driverstack.yunos.remote.vo.ConfigurationItem toRemoteVO() {
		com.driverstack.yunos.remote.vo.ConfigurationItem remoteItem = new com.driverstack.yunos.remote.vo.ConfigurationItem();
		remoteItem.setName(name);
		remoteItem.setType(type);
		remoteItem.setValue(value);

		return remoteItem;
	}

}
