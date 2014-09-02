package com.driverstack.yunos.service;

import java.io.InputStream;
import java.io.Serializable;

import com.driverstack.yunos.domain.Driver;


public interface DriverService {
	/**
	 * developer upload driver.jar file. validate it. if it is ok, save it to
	 * file system and create a record in DB.
	 * 
	 * @param driverJarFile
	 */
	Serializable upload(InputStream stream);

	void delete(Serializable id);
}
