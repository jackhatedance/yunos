package com.deviceyun.smarthome;

import java.io.InputStream;
import java.util.Properties;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import com.deviceyun.smarthome.api.device.Model;
import com.deviceyun.smarthome.api.driver.Driver;

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
public class DriverManagerImpl implements DriverManager {

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
