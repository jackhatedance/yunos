package com.driverstack.yunos.driver;

import java.util.Properties;

public class DriverProperties {

	public static final String NAME = "name";
	public static final String VERSION = "version";
	public static final String CLASS_NAME = "className";
	public static final String SDK_VERSION = "sdkVersion";
	public static final String AUTHOR_NAME = "authorName";
	public static final String AUTHOR_EMAIL = "authorEmail";

	private Properties props;

	public DriverProperties(Properties props) {
		this.props = props;
	}

	public String getName() {
		return props.getProperty(NAME);
	}

	public String getVersion() {
		return props.getProperty(VERSION);
	}

	public String getClassName() {
		return props.getProperty(CLASS_NAME);
	}

	public String getSdkVersion() {
		return props.getProperty(SDK_VERSION);
	}

	public String getAuthorName() {
		return props.getProperty(AUTHOR_NAME);
	}

	public String getAuthorEmail() {
		return props.getProperty(AUTHOR_EMAIL);
	}

}
