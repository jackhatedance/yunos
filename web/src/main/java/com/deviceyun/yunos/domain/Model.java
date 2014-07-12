package com.deviceyun.yunos.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * it is a entity.
 * 
 * @author jack
 * 
 */
@javax.persistence.Entity
@Table(name = "models")
public class Model {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	@Column
	private String brand;

	@Column
	private String product;
	@Column
	private String model;

	@Column
	private String description;

	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "compatible_models", joinColumns = { @JoinColumn(name = "modelId") }, inverseJoinColumns = { @JoinColumn(name = "compatibleModelId") })
	private Set<Model> compatibleModels = new HashSet<Model>();

	private String configure;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public com.deviceyun.yunos.api.device.Model getVO() {
		com.deviceyun.yunos.api.device.Model m = new com.deviceyun.yunos.api.device.Model(
				brand, product, model);
		return m;
	}
}
