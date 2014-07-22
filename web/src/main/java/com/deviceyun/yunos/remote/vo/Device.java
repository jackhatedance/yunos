package com.deviceyun.yunos.remote.vo;

import org.springframework.beans.BeanUtils;

public class Device {
	private String id;

	private com.deviceyun.yunos.remote.vo.Model model;
	private String revision;
	private String mfgSerialNumber;

	private String factoryConfigure;

	private String userConfigure;

	private String deviceState;

	private String name;

	private String location;

	private String description;

	public Device(com.deviceyun.yunos.domain.Device domainDevice) {

		BeanUtils.copyProperties(domainDevice, this, "model");
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public com.deviceyun.yunos.remote.vo.Model getModel() {
		return model;
	}

	public void setModel(com.deviceyun.yunos.remote.vo.Model model) {
		this.model = model;
	}

	public String getRevision() {
		return revision;
	}

	public void setRevision(String revision) {
		this.revision = revision;
	}

	public String getMfgSerialNumber() {
		return mfgSerialNumber;
	}

	public void setMfgSerialNumber(String mfgSerialNumber) {
		this.mfgSerialNumber = mfgSerialNumber;
	}

	public String getFactoryConfigure() {
		return factoryConfigure;
	}

	public void setFactoryConfigure(String factoryConfigure) {
		this.factoryConfigure = factoryConfigure;
	}

	public String getUserConfigure() {
		return userConfigure;
	}

	public void setUserConfigure(String userConfigure) {
		this.userConfigure = userConfigure;
	}

	public String getDeviceState() {
		return deviceState;
	}

	public void setDeviceState(String deviceState) {
		this.deviceState = deviceState;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
