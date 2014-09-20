package com.driverstack.yunos.remote.vo;

import java.io.Serializable;

public class Driver implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9146674463860579186L;

	private String id;

	private String name;

	private String version;

	private String developerName;

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

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getDeveloperName() {
		return developerName;
	}

	public void setDeveloperName(String developerName) {
		this.developerName = developerName;
	}

	public Driver() {

	}

	public Driver(String id, String name, String version, String developerName) {
		this.id = id;
		this.name = name;
		this.version = version;
		this.developerName = developerName;
	}

	@Override
	public String toString() {
		return String.format("%s-%s-%s", developerName, name, version);

	}

}
