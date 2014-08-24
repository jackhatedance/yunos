package com.deviceyun.yunos.domain;

import java.util.HashMap;
import java.util.Locale;
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

import org.hibernate.annotations.GenericGenerator;

/**
 * it is a entity.
 * 
 * @author jack
 * 
 */
@javax.persistence.Entity
public class DriverConfigureItem {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	@JoinColumn(name = "driverId")
	@ManyToOne(cascade = CascadeType.ALL)
	private Driver driver;

	@Column
	private String name;

	@Column
	private String description;

	@Column
	private String type;

	@Column
	private String constraints;

	@Column
	private String locale;

	@JoinColumn(name = "primaryId")
	@ManyToOne(cascade = CascadeType.ALL)
	private DriverConfigureItem primary;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "primary")
	@MapKey(name = "locale")
	private Map<String, DriverConfigureItem> locales = new HashMap<String, DriverConfigureItem>();

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

	public DriverConfigureItem getPrimary() {
		return primary;
	}

	public void setPrimary(DriverConfigureItem primary) {
		this.primary = primary;
	}

	public Map<String, DriverConfigureItem> getLocales() {
		return locales;
	}

	public void setLocales(Map<String, DriverConfigureItem> locales) {
		this.locales = locales;
	}

	@Override
	public String toString() {
		return name;
	}

	public DriverConfigureItem get(String locale) {
		DriverConfigureItem lb = locales.get(locale);
		if (lb != null)
			return lb;
		else
			return this;
	}
}
