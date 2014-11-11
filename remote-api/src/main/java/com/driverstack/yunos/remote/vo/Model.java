package com.driverstack.yunos.remote.vo;

import java.io.Serializable;

/**
 * it is a entity.
 * 
 * @author jack
 * 
 */
public class Model implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1163337070537069446L;

	private String id;

	private String name;

	private String description;

	public Model() {
	}

	public Model(String id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}

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
