package com.driverstack.yunos.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import com.driverstack.yunos.remote.vo.ConfigurationItem;

@Entity
public class DeviceConfigurationItem {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	@Column
	private String name;
	@Column
	private String type;
	@Column
	private String value;

	@JoinColumn(name = "deviceId")
	@ManyToOne(cascade = CascadeType.ALL)
	private Device device;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public DeviceConfigurationItem() {
	}

	public DeviceConfigurationItem(ConfigurationItem remoteConfigurationItem) {
		this.name = remoteConfigurationItem.getName();
		this.type = remoteConfigurationItem.getType();
		this.value = remoteConfigurationItem.getValue();
	}
	
	public ConfigurationItem toRemoteVO(){
		ConfigurationItem remoteItem = new ConfigurationItem();
		remoteItem.setName(name);
		remoteItem.setType(type);
		remoteItem.setValue(value);
		
		return remoteItem;
	}
	
}
