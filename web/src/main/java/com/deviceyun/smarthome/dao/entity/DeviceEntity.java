package com.deviceyun.smarthome.dao.entity;

import org.json.JSONObject;

import com.deviceyun.smarthome.api.device.DeviceInfo;

/**
 * physical device
 * 
 * @author jackding
 * 
 */
public class DeviceEntity {
	private String id;

	private ModelEntity model;
	private String revision;
	private String mfgSerialNumber;
	private JSONObject configure;

	public ModelEntity getModel() {
		return model;
	}

	public void setModel(ModelEntity model) {
		this.model = model;
	}

	public String getRevision() {
		return revision;
	}

	public void setRevision(String revision) {
		this.revision = revision;
	}

	public DeviceInfo getInfo() {
		// merge model configure and device configure
		JSONObject modelConfig = model.getConfigure();
		JSONObject deviceConfig = configure;

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
