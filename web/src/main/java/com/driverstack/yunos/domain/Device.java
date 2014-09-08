package com.driverstack.yunos.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.driverstack.yunos.device.DeviceInfo;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * physical device
 * 
 * @author jackding
 * 
 */
@javax.persistence.Entity
@Table
public class Device {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	@JoinColumn(name = "modelId")
	@ManyToOne(cascade = CascadeType.ALL)
	private Model model;
	@Column
	private String revision;
	@Column
	private String mfgSerialNumber;

	/**
	 * factory configure
	 */
	@Column
	private String factoryConfigure;

	@JoinColumn(name = "userId")
	@ManyToOne(cascade = CascadeType.ALL)
	private User user;

	@JoinColumn(name = "driverId")
	@ManyToOne(cascade = CascadeType.ALL)
	private Driver driver;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "device")
	@MapKey(name = "name")
	private Map<String, DeviceConfigurationItem> userConfigurationItems = new HashMap<String, DeviceConfigurationItem>();

	@Column
	private String deviceState;

	@Column
	private String name;
	@Column
	private String location;
	@Column
	private String description;

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public String getRevision() {
		return revision;
	}

	public void setRevision(String revision) {
		this.revision = revision;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMfgSerialNumber() {
		return mfgSerialNumber;
	}

	public void setMfgSerialNumber(String mfgSerialNumber) {
		this.mfgSerialNumber = mfgSerialNumber;
	}

	public String getFactoryConfigure() {
		return factoryConfigure;
	}

	public void setFactoryConfigure(String factoryConfigure) {
		this.factoryConfigure = factoryConfigure;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public Map<String, DeviceConfigurationItem> getUserConfigurationItems() {
		return userConfigurationItems;
	}

	public void setUserConfigurationItems(
			Map<String, DeviceConfigurationItem> userConfigurationItems) {
		this.userConfigurationItems = userConfigurationItems;
	}

	public String getDeviceState() {
		return deviceState;
	}

	public void setDeviceState(String deviceState) {
		this.deviceState = deviceState;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * override model configure by device factory configure then user configure
	 * 
	 * @return
	 */
	public JsonObject getFinalConfigure() {

		String modelConfigStr = model.getConfigure();
		String deviceConfigStr = this.factoryConfigure;
		// String userConfigStr = this.getUserConfigure();
		String userConfigStr = null;

		if (modelConfigStr == null)
			modelConfigStr = "{}";

		if (deviceConfigStr == null)
			deviceConfigStr = "{}";

		if (userConfigStr == null)
			userConfigStr = "{}";

		JsonParser jsonParser = new JsonParser();
		JsonObject modelConfig = jsonParser.parse(modelConfigStr)
				.getAsJsonObject();
		JsonObject deviceConfig = jsonParser.parse(deviceConfigStr)
				.getAsJsonObject();
		JsonObject userConfig = jsonParser.parse(userConfigStr)
				.getAsJsonObject();

		JsonObject computedConfig = new JsonObject();
		for (Entry<String, JsonElement> entry : modelConfig.entrySet()) {
			computedConfig.add(entry.getKey(), entry.getValue());
		}
		// overwrite with device configure
		for (Entry<String, JsonElement> entry : deviceConfig.entrySet()) {
			computedConfig.add(entry.getKey(), entry.getValue());
		}

		// overwrite with user configuration
		for (Entry<String, JsonElement> entry : userConfig.entrySet()) {
			computedConfig.add(entry.getKey(), entry.getValue());
		}

		return computedConfig;
	}

	public DeviceInfo getInfo() {

		return new DeviceInfo(id, model.getVO(), getFinalConfigure());

	}

	public void addConfigurationItem(DeviceConfigurationItem item) {
		item.setDevice(this);
		userConfigurationItems.put(item.getName(), item);
	}

}
