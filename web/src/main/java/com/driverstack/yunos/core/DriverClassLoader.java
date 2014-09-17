package com.driverstack.yunos.core;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.xeustechnologies.jcl.DelegateProxyClassLoader;
import org.xeustechnologies.jcl.JarClassLoader;
import org.xeustechnologies.jcl.JclObjectFactory;
import org.xeustechnologies.jcl.ProxyClassLoader;
import org.xeustechnologies.jcl.proxy.CglibProxyProvider;
import org.xeustechnologies.jcl.proxy.ProxyProviderFactory;

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

	@Autowired
	private ResoucePath resourceFolder;

	/**
	 * key is driver Id; caution: memory leak happens here.
	 */
	protected Map<String, JarClassLoader> classLoaderCache = new HashMap<String, JarClassLoader>();

	private JclObjectFactory factory;

	private JarClassLoader deviceApiClassLoader;
	private ProxyClassLoader deviceApiClassLoaderDelegate;

	@PostConstruct
	public void init() {

		// Set default to cglib (from version 2.2.1)
		ProxyProviderFactory.setDefaultProxyProvider(new CglibProxyProvider());

		// Create a factory of castable objects/proxies
		factory = JclObjectFactory.getInstance(true);

		// add jar file list later
		deviceApiClassLoader = new JarClassLoader();
		deviceApiClassLoader.add(resourceFolder.getApiPath());

		deviceApiClassLoaderDelegate = new DelegateProxyClassLoader(
				deviceApiClassLoader);
		deviceApiClassLoaderDelegate.setOrder(6);

	}

	public com.driverstack.yunos.driver.Driver loadDriver(
			com.driverstack.yunos.domain.Driver driver) {

		JarClassLoader jcl = classLoaderCache.get(driver.getId());
		if (jcl == null) {
			jcl = new JarClassLoader();

			String driverFileName = String.format("%s/%s-%s.jar",
					driver.getDeveloperName(), driver.getName(),
					driver.getVersion());
			// String driverFileName = driver.getId() + ".jar";

			jcl.add(resourceFolder.getDriverPath() + driverFileName);

			jcl.addLoader(deviceApiClassLoaderDelegate);

			classLoaderCache.put(driver.getId(), jcl);

		}

		return (com.driverstack.yunos.driver.Driver) factory.create(jcl,
				driver.getClassName());
	}

	/**
	 * just load it from stream and create a new instance. it is for uploading
	 * or testing.
	 * 
	 * @param input
	 * @param className
	 * @return
	 */
	public com.driverstack.yunos.driver.Driver loadDriver(InputStream input,
			String className) {

		JarClassLoader jcl = new JarClassLoader();

		jcl.add(input);

		return (com.driverstack.yunos.driver.Driver) factory.create(jcl,
				className);
	}
}
