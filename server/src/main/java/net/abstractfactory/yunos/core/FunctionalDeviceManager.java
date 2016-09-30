package net.abstractfactory.yunos.core;

import java.io.InputStream;

import net.abstractfactory.yunos.driver.i18n.bundle.ResourceBundles;

public interface FunctionalDeviceManager {

	/**
	 * read the main info(driver.properties) file.
	 * 
	 * @param input
	 * @return
	 */
	ResourceBundles readFunctionalDeviceInfoFromJarFile(InputStream input);

}