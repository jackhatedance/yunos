package com.driverstack.yunos.domain;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * for example, a device API is defined for a certain product, but not for a
 * specific model.
 * 
 * @author jackding
 * 
 */
@javax.persistence.Entity
@Table
public class FunctionalDevice {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	@JoinColumn(name = "organizationId")
	@ManyToOne(cascade = CascadeType.ALL)
	private Vendor orgnization;
	@Column
	private String className;
	@Column
	private String sdkVersion;

	@JoinColumn(name = "submitUserId")
	@ManyToOne(cascade = CascadeType.ALL)
	private User submitter;
	@Column
	private Date submitTime;
	@Column
	private String defaultLocale;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "functionalDevice")
	@MapKey(name = "locale")
	private Map<String, LocalFunctionalDevice> localFunctionalDevices = new HashMap<String, LocalFunctionalDevice>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Vendor getOrgnization() {
		return orgnization;
	}

	public void setOrgnization(Vendor orgnization) {
		this.orgnization = orgnization;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getSdkVersion() {
		return sdkVersion;
	}

	public void setSdkVersion(String sdkVersion) {
		this.sdkVersion = sdkVersion;
	}

	public User getSubmitter() {
		return submitter;
	}

	public void setSubmitter(User submitter) {
		this.submitter = submitter;
	}

	public Date getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}

	public String getDefaultLocale() {
		return defaultLocale;
	}

	public void setDefaultLocale(String defaultLocale) {
		this.defaultLocale = defaultLocale;
	}

	public Map<String, LocalFunctionalDevice> getLocalFunctionalDevices() {
		return localFunctionalDevices;
	}

	public void setLocalFunctionalDevices(
			Map<String, LocalFunctionalDevice> localFunctionalDevices) {
		this.localFunctionalDevices = localFunctionalDevices;
	}

	public FunctionalDevice() {

	}

	public FunctionalDevice(Vendor orgnization, String className,
			String sdkVersion, User submitter, Date submitTime,
			String defaultLocale) {
		this.orgnization = orgnization;
		this.className = className;
		this.sdkVersion = sdkVersion;
		this.submitter = submitter;
		this.submitTime = submitTime;
		this.defaultLocale = defaultLocale;
	}

	public void addLocalFunctionalDevice(
			LocalFunctionalDevice localFunctionalDevice) {
		localFunctionalDevice.setFunctionalDevice(this);

		localFunctionalDevices.put(localFunctionalDevice.getLocale(),
				localFunctionalDevice);
	}

}
