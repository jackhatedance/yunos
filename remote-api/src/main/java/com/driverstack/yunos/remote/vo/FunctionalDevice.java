package com.driverstack.yunos.remote.vo;

public class FunctionalDevice {

	private String organizationId;
	private String artifactId;

	private String organizationName;
	private String artifactName;

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

	public FunctionalDevice(String organizationId, String artifactId,
			String organizationName, String artifactName) {
		this.organizationId = organizationId;
		this.artifactId = artifactId;
		this.organizationName = organizationName;
		this.artifactName = artifactName;
	}
}
