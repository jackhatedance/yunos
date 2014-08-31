package com.driverstack.yunos.remote.vo;

import java.io.Serializable;

/**
 * it is a entity.
 * 
 * @author jack
 * 
 */
public class Product implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1317119235852319538L;

	private String id;

	private String name;

	private String description;

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

}
