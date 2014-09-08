package com.driverstack.yunos.domain;

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

import com.driverstack.yunos.driver.config.ConfigurationItem;

/**
 * it is a entity.
 * 
 * @author jack
 * 
 */
@javax.persistence.Entity
public class DriverConfigurationDefinitionItem {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	
	@JoinColumn(name = "definitionId")
	@ManyToOne(cascade = CascadeType.ALL)
	private DriverConfigurationDefinition configurationDefinition;

	@Column(name="\"order\"")
	private int order;
	
	@Column
	private String name;
	/**
	 * display name
	 */
	@Column
	private String displayName;

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
	private DriverConfigurationDefinitionItem primary;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "primary")
	@MapKey(name = "locale")
	private Map<String, DriverConfigurationDefinitionItem> locales = new HashMap<String, DriverConfigurationDefinitionItem>();

	public DriverConfigurationDefinitionItem() {

	}

	public DriverConfigurationDefinitionItem(
			DriverConfigurationDefinition configurationDefinition, int order,
			String name, String displayName, String description, String type,
			String constraints, String locale) {
		this.configurationDefinition = configurationDefinition;
		this.order = order;
		this.name = name;
		this.displayName = displayName;
		this.description = description;
		this.type = type;
		this.constraints = constraints;
		this.locale = locale;

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public DriverConfigurationDefinition getConfigurationDefinition() {
		return configurationDefinition;
	}

	public void setConfigurationDefinition(
			DriverConfigurationDefinition configurationDefinition) {
		this.configurationDefinition = configurationDefinition;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	 

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getConstraints() {
		return constraints;
	}

	public void setConstraints(String constraints) {
		this.constraints = constraints;
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

	public DriverConfigurationDefinitionItem getPrimary() {
		return primary;
	}

	public void setPrimary(DriverConfigurationDefinitionItem primary) {
		this.primary = primary;
	}

	public Map<String, DriverConfigurationDefinitionItem> getLocales() {
		return locales;
	}

	public void setLocales(
			Map<String, DriverConfigurationDefinitionItem> locales) {
		this.locales = locales;
	}

	public void addLocales(String locale, DriverConfigurationDefinitionItem item) {
		locales.put(locale, item);
		item.setPrimary(this);		
	}

	@Override
	public String toString() {
		return name;
	}

	public DriverConfigurationDefinitionItem get(String locale) {
		DriverConfigurationDefinitionItem lb = locales.get(locale);
		if (lb != null)
			return lb;
		else
			return this;
	}
}
