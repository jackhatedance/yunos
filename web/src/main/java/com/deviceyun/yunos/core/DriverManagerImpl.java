package com.deviceyun.yunos.core;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.deviceyun.yunos.dao.DriverDao;
import com.deviceyun.yunos.device.Model;
import com.deviceyun.yunos.domain.Device;
import com.deviceyun.yunos.driver.Driver;

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
	private DriverClassLoader driverClassLoader;

	public void setDriverDao(DriverDao driverDao) {
		this.driverDao = driverDao;
	}

	public DriverClassLoader getDriverClassLoader() {
		return driverClassLoader;
	}

	public void setDriverClassLoader(DriverClassLoader driverClassLoader) {
		this.driverClassLoader = driverClassLoader;
	}

	public DriverDao getDriverDao() {
		return driverDao;
	}

	public Driver findDriver(Model model) {

		List<com.deviceyun.yunos.domain.Driver> drivers = driverDao
				.findDriver(model);
		com.deviceyun.yunos.domain.Driver domainDriver = drivers.get(0);

		Driver driver = driverClassLoader.loadDriver(domainDriver);
		return driver;
	}

	@Override
	public Driver loadDriver(Device deviceEntity) {
		Driver driver = driverClassLoader.loadDriver(deviceEntity.getDriver());
		return driver;

	}

	public Properties readDriverInfoFromJarFile(File file) {
		Properties prop = null;
		JarFile jarFile;
		try {
			jarFile = new JarFile(file);
			JarEntry entry = jarFile.getJarEntry("driver.properties");
			InputStream stream = jarFile.getInputStream(entry);
			prop = new Properties();
			prop.load(stream);

			jarFile.close();
		} catch (Exception e) {

			throw new RuntimeException(e);
		}

		return prop;
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
	public Driver loadDriver(com.deviceyun.yunos.domain.Driver driverEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	 

}
