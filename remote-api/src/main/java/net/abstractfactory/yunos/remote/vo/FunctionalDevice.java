package net.abstractfactory.yunos.remote.vo;

import java.io.Serializable;

public class FunctionalDevice implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7964179447087234634L;

	private String deviceId;
	private String deviceName;

	private int index;
	private String organizationId;
	private String artifactId;

	private String organizationName;
	private String artifactName;

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public String getArtifactId() {
		return artifactId;
	}

	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getArtifactName() {
		return artifactName;
	}

	public void setArtifactName(String artifactName) {
		this.artifactName = artifactName;
	}

	public FunctionalDevice() {

	}

	public FunctionalDevice(String deviceId, String deviceName, int index,
			String organizationId, String artifactId, String organizationName,
			String artifactName) {
		this.deviceId = deviceId;
		this.deviceName = deviceName;
		this.index = index;
		this.organizationId = organizationId;
		this.artifactId = artifactId;
		this.organizationName = organizationName;
		this.artifactName = artifactName;
	}

	public String getFullId() {
		return String.format("%s:%d", deviceId, index);
	}
}
