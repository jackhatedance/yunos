package com.deviceyun.yunos.core;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.xeustechnologies.jcl.JarClassLoader;
import org.xeustechnologies.jcl.JclObjectFactory;
import org.xeustechnologies.jcl.proxy.CglibProxyProvider;
import org.xeustechnologies.jcl.proxy.ProxyProviderFactory;

import com.deviceyun.yunos.api.driver.Driver;

/**
 * load device driver from user uploaded jar file. we allow multiple versions
 * for same driver ID, so class names could conflict if they are loaded by
 * common class loader. to solve this problem, we create one class loader for
 * each driver.
 * 
 * @author jack
 * 
 */
@Component
public class DriverClassLoader {

	@Value("${driver.path}")
	private String driverPath = null;
	/**
	 * key is driver Id
	 */
	protected Map<String, JarClassLoader> classLoaderCache = new HashMap<String, JarClassLoader>();

	private JclObjectFactory factory;

	public DriverClassLoader() {
		// Set default to cglib (from version 2.2.1)
		ProxyProviderFactory.setDefaultProxyProvider(new CglibProxyProvider());

		// Create a factory of castable objects/proxies
		factory = JclObjectFactory.getInstance(true);
	}

	public void setDriverPath(String driverPath) {

		if (!driverPath.endsWith("/"))
			driverPath += "/";

		this.driverPath = driverPath;
	}

	public Driver loadDriver(com.deviceyun.yunos.domain.Driver driver) {

		JarClassLoader jcl = classLoaderCache.get(driver.getId());
		if (jcl == null) {
			jcl = new JarClassLoader();

			// String driverFileName = String.format("%s-%s-%s.jar",
			// driver.getAuthor(), driver.getName(), driver.getVersion());
			String driverFileName = driver.getId() + ".jar";

			jcl.add(driverPath + driverFileName);

			classLoaderCache.put(driver.getId(), jcl);
		}

		return (Driver) factory.create(jcl, driver.getClassName());
	}
}
