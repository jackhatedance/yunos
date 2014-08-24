package com.deviceyun.yunos.remote.vo;

import java.io.Serializable;

public class DriverConfigureItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6202269340588148927L;

	private String id;

	private String name;

	private String description;

	private String type;

	private String constraints;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getConstraints() {
		return constraints;
	}

	public void setConstraints(String constraints) {
		this.constraints = constraints;
	}

}
