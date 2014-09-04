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
	private String name;

	@Column
	private String description;

	@Column
	private String locale;

	@JoinColumn(name = "primaryId")
	@ManyToOne(cascade = CascadeType.ALL)
	private DeviceClass primary;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "primary")
	@MapKey(name = "locale")
	private Map<String, DeviceClass> locales = new HashMap<String, DeviceClass>();

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

	public DeviceClass getPrimary() {
		return primary;
	}

	public void setPrimary(DeviceClass primary) {
		this.primary = primary;
	}

	public Map<String, DeviceClass> getLocales() {
		return locales;
	}

	public void setLocales(Map<String, DeviceClass> locales) {
		this.locales = locales;
	}

	public DeviceClass get(String locale) {
		DeviceClass lb = locales.get(locale);
		if (lb != null)
			return lb;
		else
			return this;
	}
}
