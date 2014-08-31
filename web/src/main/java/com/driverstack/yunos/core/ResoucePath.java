package com.driverstack.yunos.core;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ResoucePath {
	@Value("${resource.path}")
	private String resourcePath = null;

	public String getResourcePath() {
		return resourcePath;
	}

	public void setResourcePath(String resourcePath) {

		if (!resourcePath.endsWith("/"))
			this.resourcePath = resourcePath + "/";
		else
			this.resourcePath = resourcePath;

	}

	public String getDriverPath() {
		return resourcePath + "driver/";
	}

	public String getApiPath() {
		return resourcePath + "api/";
	}
}
