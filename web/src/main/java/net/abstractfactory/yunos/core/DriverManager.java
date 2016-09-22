package net.abstractfactory.yunos.core;

import java.io.File;
import java.io.InputStream;
import java.util.Properties;

import net.abstractfactory.yunos.domain.Device;
import net.abstractfactory.yunos.driver.Driver;
import net.abstractfactory.yunos.driver.device.Model;

public interface DriverManager {

	/**
	 * read the main info(driver.properties) file.
	 * 
	 * @param input
	 * @return
	 */
	Properties readDriverInfoFromJarFile(InputStream input);

	/**
	 * get a instance of driver.
	 * 
	 * used for registerDriver
	 * 
	 * @param driverEntity
	 * @return
	 */
	Driver loadDriver(net.abstractfactory.yunos.domain.Driver driverEntity);

	/**
	 * load device driver object
	 * 
	 * @param deviceEntity
	 * @return
	 */
	Driver loadDriver(Device deviceEntity);

}