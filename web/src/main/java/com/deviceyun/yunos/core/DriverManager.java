package com.deviceyun.yunos.core;

import java.io.File;
import java.io.InputStream;
import java.util.Properties;

import com.deviceyun.yunos.device.Model;
import com.deviceyun.yunos.driver.Driver;
import com.deviceyun.yunos.domain.Device;

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
	Driver loadDriver(com.deviceyun.yunos.domain.Driver driverEntity);

	/**
	 * find best device driver for model
	 * 
	 * @param model
	 * @return
	 */
	Driver findDriver(Model model);

	/**
	 * load device driver object
	 * 
	 * @param deviceEntity
	 * @return
	 */
	Driver loadDriver(Device deviceEntity);

}