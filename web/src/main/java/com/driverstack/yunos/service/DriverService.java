package com.driverstack.yunos.service;

import java.io.InputStream;


public interface DriverService {
	/**
	 * developer upload driver.jar file. validate it. if it is ok, save it to
	 * file system and create a record in DB.
	 * 
	 * @param driverJarFile
	 */
	void upload(InputStream stream);

}
