package com.driverstack.yunos.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * it is a entity.
 * 
 * @author jack
 * 
 */
@javax.persistence.Entity
public class DeviceClass {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	@Column
	private String defaultLocale;

	/**
	 * current locale
	 */
	@Transient
	private String locale;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "deviceClass")
	@MapKey(name = "locale")
	private Map<String, LocalDeviceClass> localDeviceClasses = new HashMap<String, LocalDeviceClass>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getName() {
		return getLocalDeviceClass().getName();
	}

	public void setName(String name) {
		this.getLocalDeviceClass().setName(name);
	}

	public String getDescription() {
		return getLocalDeviceClass().getDescription();
	}

	public void setDescription(String description) {
		this.getLocalDeviceClass().setDescription(description);
	}

	public String getDefaultLocale() {
		return defaultLocale;
	}

	public void setDefaultLocale(String defaultLocale) {
		this.defaultLocale = defaultLocale;
	}

	public Map<String, LocalDeviceClass> getLocalDeviceClasses() {
		return localDeviceClasses;
	}

	public void setLocalDeviceClasses(
			Map<String, LocalDeviceClass> localeDeviceClasses) {
		this.localDeviceClasses = localeDeviceClasses;
	}

	public LocalDeviceClass getLocalDeviceClass(String locale) {
		LocalDeviceClass l = localDeviceClasses.get(locale);
		if (l == null)
			l = localDeviceClasses.get(defaultLocale);

		return l;
	}

	/**
	 * depends on current locale value
	 * 
	 * @return
	 */
	public LocalDeviceClass getLocalDeviceClass() {
		return getLocalDeviceClass(locale);
	}

	public DeviceClass get(String locale) {
		setLocale(locale);

		return this;
	}

}
