package com.driverstack.yunos.core;

import java.io.File;
import java.io.InputStream;
import java.util.Properties;

import com.driverstack.yunos.device.Model;
import com.driverstack.yunos.domain.Device;
import com.driverstack.yunos.driver.Driver;

public interface DriverManager {

	/**
	 * read the main info(driver.properties) file.
	 * 
	 * @param input
	 * @return
	 */
	Properties readDriverInfoFromJarFile(File file);

	Properties readDriverInfoFromJarFile(InputStream input);

	/**
	 * get a instance of driver.
	 * 
	 * used for registerDriver
	 * 
	 * @param driverEntity
	 * @return
	 */
	Driver loadDriver(com.driverstack.yunos.domain.Driver driverEntity);

	/**
	 * load device driver object
	 * 
	 * @param deviceEntity
	 * @return
	 */
	Driver loadDriver(Device deviceEntity);

}