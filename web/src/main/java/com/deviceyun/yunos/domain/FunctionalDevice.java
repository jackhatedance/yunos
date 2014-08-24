package com.deviceyun.yunos.domain;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * for example, a device API is defined for a certain product, but not for a
 * specific model.
 * 
 * @author jackding
 * 
 */
@javax.persistence.Entity
@Table
public class FunctionalDevice {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	@Column
	private String brand;
	@Column
	private String category;
	@Column
	private String product;

	@OneToMany(mappedBy = "functionalDevice", cascade = { CascadeType.ALL })
	@MapKey(name = "version")
	private Map<String, FunctionalDeviceApi> apis = new HashMap<String, FunctionalDeviceApi>();

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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public Map<String, FunctionalDeviceApi> getVersions() {
		return apis;
	}

	public void setVersions(Map<String, FunctionalDeviceApi> versions) {
		this.apis = versions;
	}

}
