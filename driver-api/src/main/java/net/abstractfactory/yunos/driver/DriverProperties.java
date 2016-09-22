package net.abstractfactory.yunos.driver;

import java.util.Properties;

public class DriverProperties {

	public static final String NAME = "name";
	public static final String VERSION = "version";
	public static final String CLASS_NAME = "className";
	public static final String SDK_VERSION = "sdkVersion";
	public static final String DEVELOPER_NAME = "developer.name";
	public static final String DEVELOPER_EMAIL = "developer.email";
	public static final String DEVELOPER_WEB = "developer.web";

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

	public String getDeveloperName() {
		return props.getProperty(DEVELOPER_NAME);
	}

	public String getDeveloperEmail() {
		return props.getProperty(DEVELOPER_EMAIL);
	}

	public String getDeveloperWeb() {
		return props.getProperty(DEVELOPER_WEB);
	}
}
