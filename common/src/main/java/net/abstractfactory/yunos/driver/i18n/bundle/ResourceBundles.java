package net.abstractfactory.yunos.driver.i18n.bundle;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ResourceBundles {

	private Map<String, Properties> propMap = new HashMap<String, Properties>();

	private Map<String, ResourceBundle> bundleMap = new HashMap<String, ResourceBundle>();

	public ResourceBundles(Map<String, Properties> propMap) {
		this.propMap = propMap;

		for (String locale : propMap.keySet()) {
			Properties props = propMap.get(locale);

			ResourceBundle bundle = new ResourceBundle(locale, props, this);
			bundleMap.put(locale, bundle);
		}
	}

	public ResourceBundle getBundle(String locale) {
		ResourceBundle bundle = bundleMap.get(locale);
		if (bundle == null)
			bundle = getDefaultBundle();

		return bundle;

	}

	public ResourceBundle getDefaultBundle() {
		return bundleMap.get(null);
	}
}
