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
import javax.persistence.JoinTable;
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
public class Vendor {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;


	@Column
	private String codeName;
	
	@Column
	private String defaultLocale;

	/**
	 * current locale
	 */
	@Transient
	private String locale;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "vendor")
	@MapKey(name = "locale")
	private Map<String, LocalVendor> localVendors = new HashMap<String, LocalVendor>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "VendorConfigurationItem", joinColumns = @JoinColumn(name = "vendorId"), inverseJoinColumns = @JoinColumn(name = "configurationItemId"))
	@MapKey(name = "name")
	private Map<String, ConfigurationItem> configurationItems = new HashMap<String, ConfigurationItem>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "vendor")
	private List<Model> models = new ArrayList<Model>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public String getDefaultLocale() {
		return defaultLocale;
	}

	public void setDefaultLocale(String defaultLocale) {
		this.defaultLocale = defaultLocale;
	}

	public String getShortName() {
		return getLocalVendor().getShortName();
	}

	public void setShortName(String shortName) {
		getLocalVendor().setShortName(shortName);
	}

	public String getFullName() {
		return getLocalVendor().getFullName();
	}

	public void setFullName(String fullName) {
		getLocalVendor().setShortName(fullName);
	}

	public String getDescription() {
		return getLocalVendor().getDescription();
	}

	public void setDescription(String description) {
		getLocalVendor().setDescription(description);
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public Map<String, LocalVendor> getLocalVendors() {
		return localVendors;
	}

	public void setLocalVendors(Map<String, LocalVendor> localVendors) {
		this.localVendors = localVendors;
	}

	public Map<String, ConfigurationItem> getConfigurationItems() {
		return configurationItems;
	}

	public void setConfigurationItems(
			Map<String, ConfigurationItem> configurationItems) {
		this.configurationItems = configurationItems;
	}

	public List<Model> getModels() {
		return models;
	}

	public void setModels(List<Model> models) {
		this.models = models;
	}

	@Override
	public String toString() {
		return getShortName();
	}

	public LocalVendor getLocalVendor(String locale) {
		LocalVendor vl = localVendors.get(locale);
		if (vl == null)
			vl = localVendors.get(defaultLocale);

		return vl;
	}

	/**
	 * depends on current locale value
	 * 
	 * @return
	 */
	public LocalVendor getLocalVendor() {
		return getLocalVendor(locale);
	}

	public Vendor get(String locale) {
		setLocale(locale);

		return this;
	}

	public ConfigurationItem getCalculatedConfigurationItem(String name) {
		ConfigurationItem item = configurationItems.get(name);
		return item;
	}
}
