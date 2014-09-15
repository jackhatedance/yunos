package com.driverstack.yunos.driver.config;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.SortedSet;

import com.driverstack.yunos.driver.config.annotation.Configure;
import com.driverstack.yunos.driver.config.annotation.Item;
import com.driverstack.yunos.driver.i18n.I18nString;
import com.driverstack.yunos.driver.i18n.UTF8Control;

public class ConfigureAnnotationParser {

	public com.driverstack.yunos.driver.config.ConfigurationDefinition parse(
			Class configClass) {

		ClassLoader classLoader = configClass.getClassLoader();

		Configure configureAnnotation = (Configure) configClass
				.getAnnotation(Configure.class);

		String resource = configureAnnotation.resourceFile();
		String defaultLocaleStr = configureAnnotation.defaultLocale();
		Locale defaultLocale = Locale.forLanguageTag(defaultLocaleStr.replace(
				'_', '-'));
		String[] supportedlocalesStr = configureAnnotation.supportedlocales();
		Locale[] supportedLocales = new Locale[supportedlocalesStr.length];
		for (int i = 0; i < supportedlocalesStr.length; i++)
			supportedLocales[i] = Locale.forLanguageTag(supportedlocalesStr[i]
					.replace('_', '-'));

		List<ConfigurationItem> items = new ArrayList<ConfigurationItem>();

		for (Field field : configClass.getDeclaredFields()) {

			Item item = field.getAnnotation(Item.class);
			if (item != null) {
				String name = field.getName();

				ConfigurationItemType type = ConfigurationItemType
						.getType(field.getType());

				I18nString i18nName = getI18nString(resource, classLoader,
						defaultLocale, supportedLocales, name + ".name");
				I18nString i18nDesc = getI18nString(resource, classLoader,
						defaultLocale, supportedLocales, name + ".description");

				ConfigurationItem configItem = new ConfigurationItem(name,
						i18nName, i18nDesc, type, item.defaultValue(),
						item.order());

				items.add(configItem);
			}

		}

		// sort items, re-set order value
		Collections.sort(items, new Comparator<ConfigurationItem>() {
			public int compare(ConfigurationItem o1, ConfigurationItem o2) {
				return o1.getOrder() - o2.getOrder();
			};
		});
		for (int i = 0; i < items.size(); i++)
			items.get(i).setOrder(i);

		ConfigurationDefinition configureDef = new com.driverstack.yunos.driver.config.ConfigurationDefinition(
				defaultLocale, supportedLocales, items);

		return configureDef;
	}

	private I18nString getI18nString(String resource, ClassLoader classLoader,
			Locale defaultLocale, Locale[] locales, String key) {
		// String port = bundle.getString("port.name");

		ResourceBundle bundle = ResourceBundle.getBundle(resource,
				defaultLocale, classLoader, new UTF8Control());

		I18nString i18nString = new I18nString(bundle.getString(key));

		for (Locale l : locales) {
			bundle = ResourceBundle.getBundle(resource, l, classLoader,
					new UTF8Control());

			i18nString.add(l.toString(), bundle.getString(key));

		}

		return i18nString;
	}

}
