package net.abstractfactory.yunos.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

import org.springframework.stereotype.Component;

import net.abstractfactory.yunos.driver.i18n.bundle.ResourceBundle;
import net.abstractfactory.yunos.driver.i18n.bundle.ResourceBundles;

/**
 * 
 * 
 * @author jackding
 * 
 */
@Component
public class FunctionalDeviceManagerImpl implements FunctionalDeviceManager {

	public final static String RESOURCE_NAME = "functionalDevice";
	public final static String EXTENSION = ".properties";

	private Map<String, Properties> readAllProperties(
			JarInputStream jarInputStream, String resouceName, String extension)
			throws IOException {
		Map<String, Properties> propertyMap = new HashMap<String, Properties>();

		int start = resouceName.length();
		int extensionLength = EXTENSION.length();

		JarEntry entry = null;
		while ((entry = jarInputStream.getNextJarEntry()) != null) {

			if (entry.getName().startsWith(resouceName)
					&& entry.getName().endsWith(extension)) {
				Properties prop = new Properties();
				prop.load(new InputStreamReader(jarInputStream, "UTF-8"));

				String entryName = entry.getName();

				int end = entryName.length() - extensionLength;
				String locale = entry.getName().substring(start, end);

				// default locale
				if (locale.isEmpty())
					locale = null;
				else
					locale = locale.substring(1);// remove leading underscore

				propertyMap.put(locale, prop);
			}
		}
		return propertyMap;
	}

	@Override
	public ResourceBundles readFunctionalDeviceInfoFromJarFile(InputStream input) {

		try {

			JarInputStream jarInputStream = new JarInputStream(input);

			Map<String, Properties> map = readAllProperties(jarInputStream,
					RESOURCE_NAME, EXTENSION);
			/*
			 * String main = RESOURCE_NAME + EXTENSION;
			 * FunctionalDeviceProperties mainProp = new
			 * FunctionalDeviceProperties( map.get(main));
			 * 
			 * net.abstractfactory.yunos.domain.FunctionalDevice
			 * doaminFunctionalDevice = new
			 * net.abstractfactory.yunos.domain.FunctionalDevice
			 * (mainProp.getClassName(),mainProp.getSdkVersion(), null,new
			 * Date(), mainProp.getDefaultLocale());
			 */
			ResourceBundles bundles = new ResourceBundles(map);
			return bundles;

		} catch (Exception e) {

			throw new RuntimeException(e);
		}

	}

	public String getResouceName(String locale) {
		if (locale == null || locale == "")
			return RESOURCE_NAME + EXTENSION;
		else
			return RESOURCE_NAME + "_" + locale + EXTENSION;
	}

}
