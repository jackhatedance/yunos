package com.deviceyun.yunos.driver.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ConfigurationDefinition {

	private Locale defaultLocale;
	private Locale[] supportedLocales;
	private List<ConfigurationItem> items = new ArrayList<ConfigurationItem>();

	public ConfigurationDefinition(Locale defaultLocale, Locale[] supportedLocales,
			List<ConfigurationItem> items) {
		this.defaultLocale = defaultLocale;
		this.supportedLocales = supportedLocales;
		this.items = items;
	}

	public Locale getDefaultLocale() {
		return defaultLocale;
	}

	public void setDefaultLocale(Locale defaultLocale) {
		this.defaultLocale = defaultLocale;
	}

	public Locale[] getSupportedLocales() {
		return supportedLocales;
	}

	public void setSupportedLocales(Locale[] supportedLocales) {
		this.supportedLocales = supportedLocales;
	}

	public List<ConfigurationItem> getItems() {
		return items;
	}

	public void setItems(List<ConfigurationItem> items) {
		this.items = items;
	}

	public String getDefaultLocaleTag() {
		return defaultLocale.toString();
	}

	public String[] getSupportedLocaleTags() {
		String[] sa = new String[supportedLocales.length];
		for (int i = 0; i < supportedLocales.length; i++) {
			Locale locale = supportedLocales[i];
			sa[i] = locale.toString();
		}
		return sa;
	}
}
