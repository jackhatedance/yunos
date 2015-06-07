package com.driverstack.yunos.domain;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
	private String className;

	@Column
	private String developerName;

	@Column
	private String developerEmail;

	@Column
	private String developerWeb;

	@Column
	private Date submitTime;

	@OneToOne(mappedBy = "driver", cascade = CascadeType.ALL)
	private DriverConfigurationDefinition configurationDefinition;

	@ManyToMany
	@JoinTable(name = "DriverSupportedModel", joinColumns = { @JoinColumn(name = "driverId") }, inverseJoinColumns = { @JoinColumn(name = "modelId") })
	private Set<Model> supportedModels;

	public Driver() {

	}

	public Driver(String name, String version, String sdkVersion,
			String className, String developerName, String developerEmail,
			String developerWeb) {
		this.name = name;
		this.version = version;
		this.sdkVersion = sdkVersion;
		this.className = className;
		this.developerName = developerName;
		this.developerEmail = developerEmail;
		this.developerWeb = developerWeb;

		submitTime = new Date();
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

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getDeveloperName() {
		return developerName;
	}

	public void setDeveloperName(String developerName) {
		this.developerName = developerName;
	}

	public String getDeveloperEmail() {
		return developerEmail;
	}

	public void setDeveloperEmail(String developerEmail) {
		this.developerEmail = developerEmail;
	}

	public String getDeveloperWeb() {
		return developerWeb;
	}

	public void setDeveloperWeb(String developerWeb) {
		this.developerWeb = developerWeb;
	}

	public Date getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}

	public Set<Model> getSupportedModels() {
		return supportedModels;
	}

	public void setSupportedModels(Set<Model> supportedModels) {
		this.supportedModels = supportedModels;
	}

	public DriverConfigurationDefinition getConfigurationDefinition() {
		return configurationDefinition;
	}

	public void setConfigurationDefinition(
			DriverConfigurationDefinition configurationDefinition) {
		this.configurationDefinition = configurationDefinition;

		configurationDefinition.setDriver(this);
	}

}
