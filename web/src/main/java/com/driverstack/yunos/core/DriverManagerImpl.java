package com.driverstack.yunos.core;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.driverstack.yunos.core.exception.DriverNotSetException;
import com.driverstack.yunos.dao.DriverDao;
import com.driverstack.yunos.domain.Device;
import com.driverstack.yunos.driver.Driver;

/**
 * driver manager responsibilities:
 * <ul>
 * <li>find matching driver for device.</li>
 * <li>class-loader for all of API version.</li>
 * </ul>
 * 
 * @author jackding
 * 
 */
@Component
public class DriverManagerImpl implements DriverManager {

	@Autowired
	private DriverDao driverDao;

	@Autowired
	private DriverObjectFactory driverObjectFactory;

	public void setDriverDao(DriverDao driverDao) {
		this.driverDao = driverDao;
	}

	public DriverObjectFactory getDriverObjectFactory() {
		return driverObjectFactory;
	}

	public void setDriverObjectFactory(DriverObjectFactory driverObjectFactory) {
		this.driverObjectFactory = driverObjectFactory;
	}

	public DriverDao getDriverDao() {
		return driverDao;
	}

	@Override
	public Driver loadDriver(Device domainDevice) {
		if(domainDevice.getDriver()==null)
			throw new DriverNotSetException(domainDevice.getId());
		
		Driver driver = driverObjectFactory.createDriverObject(domainDevice.getDriver());
		return driver;

	}

	private JarEntry getJarEntry(JarInputStream jarInputStream, String entryName)
			throws IOException {
		JarEntry entry = null;
		while (jarInputStream.available() > 0) {
			entry = jarInputStream.getNextJarEntry();
			if (entry.getName().equals(entryName))
				break;
		}
		return entry;
	}

	public Properties readDriverInfoFromJarFile(InputStream input) {
		Properties prop = null;

		try {

			JarInputStream jarInputStream = new JarInputStream(input);

			JarEntry entry = getJarEntry(jarInputStream, "driver.properties");

			// the whole stream is smart enough to act as entry stream.
			InputStream entryStream = jarInputStream;

			prop = new Properties();
			prop.load(entryStream);

		} catch (Exception e) {

			throw new RuntimeException(e);
		}

		return prop;
	}

	@Override
	public Driver loadDriver(com.driverstack.yunos.domain.Driver driverEntity) {
		// TODO Auto-generated method stub
		return null;
	}

}
