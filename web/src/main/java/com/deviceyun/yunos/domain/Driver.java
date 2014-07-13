package com.deviceyun.yunos.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@javax.persistence.Entity
@Table
public class Driver {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	@Column
	private String name;
	@Column
	private String version;
	@Column
	private String sdkVersion;
	@Column
	private String author;
	@Column
	private Date submitTime;

	@JoinColumn(name = "deviceApiId")
	@ManyToOne(cascade = CascadeType.ALL)
	private DeviceApi deviceApi;
	@Column
	private String deviceApiVersion;

	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "DriverSupportedModel", joinColumns = { @JoinColumn(name = "driverId") }, inverseJoinColumns = { @JoinColumn(name = "modelId") })
	private Set<Model> supportedModels;

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

	public String getSdkVersion() {
		return sdkVersion;
	}

	public void setSdkVersion(String sdkVersion) {
		this.sdkVersion = sdkVersion;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}

	public DeviceApi getDeviceApi() {
		return deviceApi;
	}

	public void setDeviceApi(DeviceApi deviceApi) {
		this.deviceApi = deviceApi;
	}

	public String getDeviceApiVersion() {
		return deviceApiVersion;
	}

	public void setDeviceApiVersion(String deviceApiVersion) {
		this.deviceApiVersion = deviceApiVersion;
	}

	public Set<Model> getSupportedModels() {
		return supportedModels;
	}

	public void setSupportedModels(Set<Model> supportedModels) {
		this.supportedModels = supportedModels;
	}

}
