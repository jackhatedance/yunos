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
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.driverstack.yunos.driver.device.DeviceInfo;
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
	@ManyToOne
	private Model model;
	@Column
	private String revision;
	@Column
	private String mfgSerialNumber;

	@JoinColumn(name = "userId")
	@ManyToOne
	private User user;

	@JoinColumn(name = "driverId")
	@ManyToOne
	private Driver driver;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "DeviceFactoryConfigurationItem", joinColumns = @JoinColumn(name = "deviceId"), inverseJoinColumns = @JoinColumn(name = "configurationItemId"))
	@MapKey(name = "name")
	private Map<String, ConfigurationItem> factoryConfigurationItems = new HashMap<String, ConfigurationItem>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "DeviceUserConfigurationItem", joinColumns = @JoinColumn(name = "deviceId"), inverseJoinColumns = @JoinColumn(name = "configurationItemId"))
	@MapKey(name = "name")
	private Map<String, ConfigurationItem> userConfigurationItems = new HashMap<String, ConfigurationItem>();

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

	public Map<String, ConfigurationItem> getFactoryConfigurationItems() {
		return factoryConfigurationItems;
	}

	public void setFactoryConfigurationItems(
			Map<String, ConfigurationItem> factoryConfigurationItems) {
		this.factoryConfigurationItems = factoryConfigurationItems;
	}

	public Map<String, ConfigurationItem> getUserConfigurationItems() {
		return userConfigurationItems;
	}

	public void setUserConfigurationItems(
			Map<String, ConfigurationItem> userConfigurationItems) {
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

		// TODO redesign it

		String modelConfigStr = null;
		String deviceConfigStr = null;
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

	public void addConfigurationItem(ConfigurationItem item) {

		userConfigurationItems.put(item.getName(), item);
	}

	public ConfigurationItem getCalculatedFactoryValue(String name) {
		ConfigurationItem item = getFactoryConfigurationItems().get(name);
		if (item != null)
			return item;
		else
			return model.getCalculatedConfigurationItem(name);
	}
}
