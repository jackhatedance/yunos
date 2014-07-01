package com.deviceyun.smarthome.api.device;

/**
 * physical device
 * 
 * @author jackding
 * 
 */
public class DeviceInfo {
	private String id;

	private Model model;
	private String revision;
	private String mfgSerialNumber;
	/**
	 * only compatible with generic device model.
	 */
	private String compatibleDevice;

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

}
