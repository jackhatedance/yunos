package com.driverstack.yunos.driver.i18n.bundle;

import java.util.Properties;

public class ResourceBundle {
	private String locale;
	private Properties props;

	private ResourceBundles bundles;

	public ResourceBundle(String locale, Properties props,
			ResourceBundles bundles) {
		this.locale = locale;
		this.props = props;
		this.bundles = bundles;
	}

	public String get(String key) {

		if (props.containsKey(key))
			return props.getProperty(key);
		else
			return bundles.getDefaultBundle().getWithoutFallback(key);

	}

	protected String getWithoutFallback(String key) {

		return props.getProperty(key);

	}

}
