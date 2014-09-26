package com.driverstack.yunos.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

/**
 * it is a entity.
 * 
 * @author jack
 * 
 */
@javax.persistence.Entity
public class LocalFunctionalDevice {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	@Column
	private String displayName;

	@Column
	private String description;

	@Column
	private String locale;

	@JoinColumn(name = "functionalDeviceId")
	@ManyToOne
	private FunctionalDevice functionalDevice;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public FunctionalDevice getFunctionalDevice() {
		return functionalDevice;
	}

	public void setFunctionalDevice(FunctionalDevice functionalDevice) {
		this.functionalDevice = functionalDevice;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public LocalFunctionalDevice() {
	
	}
	public LocalFunctionalDevice(String displayName,String description, String locale) {
		 this.displayName = displayName;
		 this.description = description;
		 this.locale = locale;
	}
}
