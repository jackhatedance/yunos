package com.driverstack.yunos.remote.vo;

public class FunctionalDevice {

	private String organization;
	private String name;

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public FunctionalDevice() {

	}

	public FunctionalDevice(String organization, String name) {
		this.organization = organization;
		this.name = name;
	}
}
