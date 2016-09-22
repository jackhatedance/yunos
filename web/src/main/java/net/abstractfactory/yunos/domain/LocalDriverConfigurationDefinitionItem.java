package net.abstractfactory.yunos.domain;

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

import org.hibernate.annotations.GenericGenerator;

/**
 * it is a entity.
 * 
 * @author jack
 * 
 */
@javax.persistence.Entity
public class LocalDriverConfigurationDefinitionItem {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	@Column
	private String displayName;

	@Column
	private String description;

	@Column
	private String locale;

	@JoinColumn(name = "itemId")
	@ManyToOne
	private DriverConfigurationDefinitionItem item;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
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

	public DriverConfigurationDefinitionItem getItem() {
		return item;
	}

	public void setItem(DriverConfigurationDefinitionItem item) {
		this.item = item;
	}

	public LocalDriverConfigurationDefinitionItem() {

	}

	public LocalDriverConfigurationDefinitionItem(String displayName,
			String description, String locale) {
		this.displayName = displayName;
		this.description = description;
		this.locale = locale;
	}
}
