package com.driverstack.yunos.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import javax.persistence.OrderBy;

import org.hibernate.annotations.GenericGenerator;

/**
 * it is a entity.
 * 
 * @author jack
 * 
 */
@javax.persistence.Entity
public class Vendor {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	@Column
	private String shortName;
	
	@Column
	private String fullName;
	
	@Column
	private String description;

	@Column
	private String locale;

	@JoinColumn(name = "primaryId")
	@ManyToOne(cascade = CascadeType.ALL)
	private Vendor primary;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "primary")
	@MapKey(name = "locale")
	private Map<String, Vendor> locales = new HashMap<String, Vendor>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "vendor")
	@OrderBy("name")
	private List<Model> models = new ArrayList<Model>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	 
	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
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

	public Vendor getPrimary() {
		return primary;
	}

	public void setPrimary(Vendor primary) {
		this.primary = primary;
	}

	public Map<String, Vendor> getLocales() {
		return locales;
	}

	public void setLocales(Map<String, Vendor> locales) {
		this.locales = locales;
	}	 

	public List<Model> getModels() {
		return models;
	}

	public void setModels(List<Model> models) {
		this.models = models;
	}

	@Override
	public String toString() {
		return shortName;
	}

	public Vendor get(String locale) {
		Vendor lb = locales.get(locale);
		if (lb != null)
			return lb;
		else
			return this;
	}
}
