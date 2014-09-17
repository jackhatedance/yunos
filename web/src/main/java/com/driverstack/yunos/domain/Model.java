package com.driverstack.yunos.domain;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * it is a entity.
 * 
 * @author jack
 * 
 */
@javax.persistence.Entity
public class Model {
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

	@JoinColumn(name = "deviceClassId")
	@ManyToOne(cascade = CascadeType.ALL)
	private DeviceClass deviceClass;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "model")
	@MapKey(name = "locale")
	private Map<String, LocalModel> localModels = new HashMap<String, LocalModel>();

	@JoinColumn(name = "vendorId")
	@ManyToOne(cascade = CascadeType.ALL)
	private Vendor vendor;

	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "CompatibleModel", joinColumns = { @JoinColumn(name = "modelId") }, inverseJoinColumns = { @JoinColumn(name = "compatibleModelId") })
	private Set<Model> compatibleModels = new HashSet<Model>();
	
	@ManyToMany(mappedBy="compatibleModels")
	private Set<Model> compliedBy = new HashSet<Model>();
	 
	/**
	 * it is a good habit to create a sample configure and save beside the
	 * actual configure. sometime we may want to take a look what it should
	 * contains.
	 */
	@Column
	private String sampleConfigure;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "ModelConfigurationItem", joinColumns = @JoinColumn(name = "modelId"), inverseJoinColumns = @JoinColumn(name = "configurationItemId"))
	@MapKey(name = "name")
	private Map<String, ConfigurationItem> configurationItems = new HashMap<String, ConfigurationItem>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	public String getName() {
		return getLocalModel().getName();

	}

	public void setName(String name) {
		getLocalModel().setName(name);
	}

	public String getDescription() {
		return getLocalModel().getDescription();
	}

	public void setDescription(String description) {
		getLocalModel().setDescription(description);
	}

	public String getSampleConfigure() {
		return sampleConfigure;
	}

	public void setSampleConfigure(String sampleConfigure) {
		this.sampleConfigure = sampleConfigure;
	}

	public Map<String, ConfigurationItem> getConfigurationItems() {
		return configurationItems;
	}

	public void setConfigurationItems(
			Map<String, ConfigurationItem> configurationItems) {
		this.configurationItems = configurationItems;
	}

	public Set<Model> getCompatibleModels() {
		return compatibleModels;
	}

	public void setCompatibleModels(Set<Model> compatibleModels) {
		this.compatibleModels = compatibleModels;
	}

	public String getDefaultLocale() {
		return defaultLocale;
	}

	public void setDefaultLocale(String defaultLocale) {
		this.defaultLocale = defaultLocale;
	}

	public Map<String, LocalModel> getLocalModels() {
		return localModels;
	}

	public void setLocalModels(Map<String, LocalModel> localModels) {
		this.localModels = localModels;
	}

	public Set<Model> getCompliedBy() {
		return compliedBy;
	}

	public void setCompliedBy(Set<Model> compliedBy) {
		this.compliedBy = compliedBy;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public DeviceClass getDeviceClass() {
		return deviceClass;
	}

	public void setDeviceClass(DeviceClass deviceClass) {
		this.deviceClass = deviceClass;
	}

	public LocalModel getLocalModel(String locale) {
		LocalModel l = localModels.get(locale);
		if (l == null)
			l = localModels.get(defaultLocale);

		return l;
	}

	/**
	 * depends on current locale value
	 * 
	 * @return
	 */
	public LocalModel getLocalModel() {
		return getLocalModel(locale);
	}

	public Model get(String locale) {
		setLocale(locale);

		return this;
	}

	public com.driverstack.yunos.device.Model getVO() {
		com.driverstack.yunos.device.Model m = new com.driverstack.yunos.device.Model(
				vendor.getShortName(), getName());
		return m;
	}

	public ConfigurationItem getCalculatedConfigurationItem(String name) {
		ConfigurationItem item = configurationItems.get(name);
		if (item != null)
			return item;
		else
			return vendor.getCalculatedConfigurationItem(name);

	}

}
