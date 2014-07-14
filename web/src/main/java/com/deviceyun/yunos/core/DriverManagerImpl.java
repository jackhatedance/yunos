package com.deviceyun.yunos.core;

import java.io.InputStream;
import java.util.Properties;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.deviceyun.yunos.api.device.Model;
import com.deviceyun.yunos.api.driver.Driver;
import com.deviceyun.yunos.dao.DriverDao;

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

	@Value("${driver.path}")
	private String driverPath = null;
	@Autowired
	private DriverDao driverDao = null;

	public void setDriverPath(String driverPath) {
		this.driverPath = driverPath;
	}

	public void setDriverDao(DriverDao driverDao) {
		this.driverDao = driverDao;
	}

	public Driver findDriver(Model model) {

		return null;
	}

	public Properties readDriverInfoFromJarFile(String fileName) {
		Properties prop = null;
		JarFile jarFile;
		try {
			jarFile = new JarFile(fileName);
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

	public static void main(String[] args) {
		DriverManagerImpl dm = new DriverManagerImpl();
		Properties prop = dm.readDriverInfoFromJarFile("/home/jack/test.jar");
		System.out.println(prop.getProperty("class-name"));
	}
}
