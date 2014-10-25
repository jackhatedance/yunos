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

	private JarClassLoader functionalDeviceClassLoader;
	private ProxyClassLoader functionalDeviceClassLoaderDelegate;

	@PostConstruct
	public void init() {

		// Set default to cglib (from version 2.2.1)
		ProxyProviderFactory.setDefaultProxyProvider(new CglibProxyProvider());

		// Create a factory of castable objects/proxies
		factory = JclObjectFactory.getInstance(true);

		// add jar file list later
		functionalDeviceClassLoader = new JarClassLoader();
		functionalDeviceClassLoader.add(resourceFolder
				.getFunctionalDevicePath());

		functionalDeviceClassLoaderDelegate = new DelegateProxyClassLoader(
				functionalDeviceClassLoader);
		functionalDeviceClassLoaderDelegate.setOrder(6);

	}

	public void addFunctionalDeviceJar(String fileName) {
		functionalDeviceClassLoader.add(resourceFolder
				.getFunctionalDevicePath() + fileName);
	}

	public com.driverstack.yunos.driver.Driver loadDriver(
			com.driverstack.yunos.domain.Driver domainDriver) {

		JarClassLoader jcl = classLoaderCache.get(domainDriver.getId());
		if (jcl == null) {
			jcl = new JarClassLoader();

			String driverFileName = String.format("%s/%s-%s.jar",
					domainDriver.getDeveloperName(), domainDriver.getName(),
					domainDriver.getVersion());
			// String driverFileName = driver.getId() + ".jar";

			jcl.add(resourceFolder.getDriverPath() + driverFileName);

			jcl.addLoader(functionalDeviceClassLoaderDelegate);

			classLoaderCache.put(domainDriver.getId(), jcl);

		}

		return (com.driverstack.yunos.driver.Driver) factory.create(jcl,
				domainDriver.getClassName());
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
		jcl.addLoader(functionalDeviceClassLoaderDelegate);
		jcl.add(input);

		return (com.driverstack.yunos.driver.Driver) factory.create(jcl,
				className);
	}

	public JarClassLoader getFunctionalDeviceClassLoader() {
		return functionalDeviceClassLoader;
	}

	public void setFunctionalDeviceClassLoader(
			JarClassLoader functionalDeviceClassLoader) {
		this.functionalDeviceClassLoader = functionalDeviceClassLoader;
	}

	public ProxyClassLoader getFunctionalDeviceClassLoaderDelegate() {
		return functionalDeviceClassLoaderDelegate;
	}

	public void setFunctionalDeviceClassLoaderDelegate(
			ProxyClassLoader functionalDeviceClassLoaderDelegate) {
		this.functionalDeviceClassLoaderDelegate = functionalDeviceClassLoaderDelegate;
	}
	
	
}
