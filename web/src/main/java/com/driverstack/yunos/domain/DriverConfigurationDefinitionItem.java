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
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

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

	@Column(name = "\"order\"")
	private int order;

	@Column
	private String name;

	@Column
	private String type;

	@Column
	private String constraints;

	@Column
	private String defaultLocale;
	/**
	 * current locale
	 */
	@Transient
	private String locale;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "item")
	@MapKey(name = "locale")
	private Map<String, LocalDriverConfigurationDefinitionItem> localItems = new HashMap<String, LocalDriverConfigurationDefinitionItem>();

	public DriverConfigurationDefinitionItem() {

	}

	public DriverConfigurationDefinitionItem(
			DriverConfigurationDefinition configurationDefinition, int order,
			String name, String type, String constraints, String defaultLocale) {
		this.configurationDefinition = configurationDefinition;
		this.order = order;
		this.name = name;

		// LocalDriverConfigurationDefinitionItem localItem = new
		// LocalDriverConfigurationDefinitionItem(displayName,description,locale);
		// this.addLocale(locale, localItem);

		this.type = type;
		this.constraints = constraints;
		this.defaultLocale = defaultLocale;

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
		return getCurentLocalItem().getDisplayName();
	}

	public void setDisplayName(String displayName) {
		getCurentLocalItem().setDisplayName(displayName);
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
		return getCurentLocalItem().getDescription();

	}

	public void setDescription(String description) {
		getCurentLocalItem().setDescription(description);

	}

	public String getDefaultLocale() {
		return defaultLocale;
	}

	public void setDefaultLocale(String defaultLocale) {
		this.defaultLocale = defaultLocale;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public LocalDriverConfigurationDefinitionItem getLocalItem(String locale) {
		LocalDriverConfigurationDefinitionItem localItem = localItems
				.get(locale);
		if (localItem == null)
			localItem = localItems.get(defaultLocale);

		return localItem;
	}

	public LocalDriverConfigurationDefinitionItem getCurentLocalItem() {
		return getLocalItem(locale);

	}

	public Map<String, LocalDriverConfigurationDefinitionItem> getLocalItems() {
		return localItems;
	}

	public void setLocalItems(
			Map<String, LocalDriverConfigurationDefinitionItem> localItems) {
		this.localItems = localItems;
	}

	public void addLocalItem(String locale,
			LocalDriverConfigurationDefinitionItem item) {
		localItems.put(locale, item);
		item.setItem(this);
	}

	public DriverConfigurationDefinitionItem get(String locale) {
		setLocale(locale);

		return this;
	}

	@Override
	public String toString() {
		return name;
	}

}
