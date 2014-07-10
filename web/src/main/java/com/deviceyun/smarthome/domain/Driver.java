package com.deviceyun.smarthome.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@javax.persistence.Entity
@Table(name = "drivers")
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

	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "supported_models", joinColumns = { @JoinColumn(name = "driverId") }, inverseJoinColumns = { @JoinColumn(name = "modelId") })
	private Set<Model> supprtedModels;

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

	public Set<Model> getSupprtedModels() {
		return supprtedModels;
	}

	public void setSupprtedModels(Set<Model> supprtedModels) {
		this.supprtedModels = supprtedModels;
	}

}
