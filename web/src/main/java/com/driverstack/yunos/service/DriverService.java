package com.driverstack.yunos.service;

import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

import com.driverstack.yunos.domain.Driver;
import com.driverstack.yunos.domain.DriverConfigurationDefinitionItem;
import com.driverstack.yunos.domain.Model;
import com.driverstack.yunos.domain.DeviceClass;


public interface DriverService {
	/**
	 * developer upload driver.jar file. validate it. if it is ok, save it to
	 * file system and create a record in DB.
	 * 
	 * @param driverJarFile
	 */
	Serializable upload(InputStream stream);

	Driver get(Serializable id);
	
	void delete(Serializable id);
	
	List<Driver> findAvailableDrivers(Model model);
	
}
