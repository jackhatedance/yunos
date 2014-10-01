package com.driverstack.yunos.remote.vo;

import java.io.Serializable;

import com.driverstack.yunos.driver.config.ConfigurationItemPrimaryType;
import com.driverstack.yunos.driver.config.ConfigurationItemType;

/**
 * 
 * 
 * @author jack
 * 
 */
public class DriverConfigurationDefinitionItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1326780189146465714L;

	private String id;
	private int order;
	private String name;
	private String displayName;
	private String description;
	private ConfigurationItemType type;
	private String constraints;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ConfigurationItemType getType() {
		return type;
	}

	public void setType(ConfigurationItemType type) {
		this.type = type;
	}

	public String getConstraints() {
		return constraints;
	}

	public void setConstraints(String constraints) {
		this.constraints = constraints;
	}

	@Override
	public String toString() {
		return name;
	}

}
