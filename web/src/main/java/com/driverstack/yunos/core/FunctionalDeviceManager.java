package com.driverstack.yunos.core;

import java.io.InputStream;

import com.driverstack.yunos.driver.i18n.bundle.ResourceBundles;

public interface FunctionalDeviceManager {

	/**
	 * read the main info(driver.properties) file.
	 * 
	 * @param input
	 * @return
	 */
	ResourceBundles readFunctionalDeviceInfoFromJarFile(InputStream input);

}