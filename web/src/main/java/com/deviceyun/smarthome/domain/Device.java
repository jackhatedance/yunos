package com.deviceyun.smarthome.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.json.JSONObject;

import com.deviceyun.smarthome.api.device.DeviceInfo;

/**
 * physical device
 * 
 * @author jackding
 * 
 */
@javax.persistence.Entity
@Table(name = "devices")
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
	@Column
	private String configure;

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

	public String getConfigure() {
		return configure;
	}

	public void setConfigure(String configure) {
		this.configure = configure;
	}

	public DeviceInfo getInfo() {
		// merge model configure and device configure
		String modelConfigStr = model.getConfigure();
		String deviceConfigStr = configure;

		JSONObject modelConfig = new JSONObject(modelConfigStr);
		JSONObject deviceConfig = new JSONObject(deviceConfigStr);

		JSONObject newConfig = new JSONObject();
		for (Object objKey : modelConfig.keySet()) {
			String key = (String) objKey;
			Object value = modelConfig.get(key);
			newConfig.put(key, value);
		}
		// overwrite with device configure
		for (Object objKey : deviceConfig.keySet()) {
			String key = (String) objKey;
			Object value = deviceConfig.get(key);
			newConfig.put(key, value);
		}

		return new DeviceInfo(id, model.getVO(), newConfig);

	}
}
