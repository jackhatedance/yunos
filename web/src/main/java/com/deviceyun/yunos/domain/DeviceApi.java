package com.deviceyun.yunos.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * it is a entity.
 * 
 * @author jack
 * 
 */
@javax.persistence.Entity
@Table
public class DeviceApi {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "functionalDeviceId")
	private FunctionalDevice functionalDevice;

	@Column
	private String version;

	@Column
	private String description;

	@Column
	private String className;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

 
	public FunctionalDevice getFunctionalDevice() {
		return functionalDevice;
	}

	public void setFunctionalDevice(FunctionalDevice functionalDevice) {
		this.functionalDevice = functionalDevice;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
