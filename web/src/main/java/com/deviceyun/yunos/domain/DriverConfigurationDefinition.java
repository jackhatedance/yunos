package com.deviceyun.yunos.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import org.apache.commons.codec.binary.StringUtils;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * it is a entity.
 * 
 * @author jack
 * 
 */
@javax.persistence.Entity
public class DriverConfigurationDefinition {
	/**
	 * same id as driver table
	 */
	@Id
	@GeneratedValue(generator = "gen")
	@GenericGenerator(name = "gen", strategy = "foreign", parameters = @Parameter(name = "property", value = "driver"))
	private String id;

	@Column
	private String defaultLocale;
	/**
	 * separated by comma
	 */
	@Column
	private String supportedLocales;

	@OneToOne
	@PrimaryKeyJoinColumn
	private Driver driver;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "configurationDefinition")
	private Set<DriverConfigurationDefinitionItem> items = new HashSet<DriverConfigurationDefinitionItem>();

	public DriverConfigurationDefinition() {

	}

	public DriverConfigurationDefinition(String defaultLocale,
			String[] supportedLocales) {
		this.defaultLocale = defaultLocale;

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < supportedLocales.length; i++) {

			if (i > 0)
				sb.append(",");

			String sl = supportedLocales[i];
			sb.append(sl);
		}

		this.supportedLocales = sb.toString();
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDefaultLocale() {
		return defaultLocale;
	}

	public void setDefaultLocale(String defaultLocale) {
		this.defaultLocale = defaultLocale;
	}

	public String getSupportedLocales() {
		return supportedLocales;
	}

	public void setSupportedLocales(String supportedLocales) {
		this.supportedLocales = supportedLocales;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public Set<DriverConfigurationDefinitionItem> getItems() {
		return items;
	}

	public void setItems(Set<DriverConfigurationDefinitionItem> items) {
		this.items = items;
	}

	public void addItem(DriverConfigurationDefinitionItem item){
		items.add(item);
	}
}
