package net.abstractfactory.yunos.service;

import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

import net.abstractfactory.yunos.domain.DeviceClass;
import net.abstractfactory.yunos.domain.Driver;
import net.abstractfactory.yunos.domain.DriverConfigurationDefinitionItem;
import net.abstractfactory.yunos.domain.Model;


public interface DriverService {
	/**
	 * developer upload driver.jar file. validate it. if it is ok, save it to
	 * file system and create a record in DB.
	 * 
	 * @param driverJarFile
	 */
	Serializable upload(InputStream in);

	Driver get(Serializable id);
	/**
	 * find drivers
	 * @param developerName
	 * @param driverName
	 * @param version
	 * @return
	 */
	List<Driver> find(String developerName, String driverName, String version);
	
	void delete(Serializable id);
	
	List<Driver> findAvailableDrivers(Model model);
	
}
