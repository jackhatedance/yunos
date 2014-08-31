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
	private String name;

	@Column
	private String description;

	@Column
	private String locale;

	@JoinColumn(name = "primaryId")
	@ManyToOne(cascade = CascadeType.ALL)
	private Model primary;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "primary")
	@MapKey(name = "locale")
	private Map<String, Model> locales = new HashMap<String, Model>();

	@JoinColumn(name = "productId")
	@ManyToOne(cascade = CascadeType.ALL)
	private Product product;

	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "compatible_models", joinColumns = { @JoinColumn(name = "modelId") }, inverseJoinColumns = { @JoinColumn(name = "compatibleModelId") })
	private Set<Model> compatibleModels = new HashSet<Model>();
	/**
	 * it is a good habit to create a sample configure and save beside the
	 * actual configure. sometime we may want to take a look what it should
	 * contains.
	 */
	@Column
	private String sampleConfigure;

	@Column
	private String configure;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
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

	public String getSampleConfigure() {
		return sampleConfigure;
	}

	public void setSampleConfigure(String sampleConfigure) {
		this.sampleConfigure = sampleConfigure;
	}

	public String getConfigure() {
		return configure;
	}

	public void setConfigure(String configure) {
		this.configure = configure;
	}

	public Set<Model> getCompatibleModels() {
		return compatibleModels;
	}

	public void setCompatibleModels(Set<Model> compatibleModels) {
		this.compatibleModels = compatibleModels;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public Model getPrimary() {
		return primary;
	}

	public void setPrimary(Model primary) {
		this.primary = primary;
	}

	public Map<String, Model> getLocales() {
		return locales;
	}

	public void setLocales(Map<String, Model> locales) {
		this.locales = locales;
	}

	public Model get(String locale){
		Model localeModel = locales.get(locale);
		if(localeModel!=null)
			return localeModel;
		else
			return this;
	}
	public com.driverstack.yunos.device.Model getVO() {
		com.driverstack.yunos.device.Model m = new com.driverstack.yunos.device.Model(
				product.getBrand().getName(), product.getName(), name);
		return m;
	}
}
