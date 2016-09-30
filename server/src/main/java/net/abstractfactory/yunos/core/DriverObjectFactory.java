package net.abstractfactory.yunos.core;

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

import net.abstractfactory.yunos.core.device.DeviceStatusChangeListener;
import net.abstractfactory.yunos.core.device.FunctionalDeviceProxy;

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
public class DriverObjectFactory {

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

	public net.abstractfactory.yunos.driver.Driver createDriverObject(
			net.abstractfactory.yunos.domain.Driver domainDriver) {

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

		return (net.abstractfactory.yunos.driver.Driver) factory.create(jcl,
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
	public net.abstractfactory.yunos.driver.Driver createDriverObject(InputStream input,
			String className) {

		JarClassLoader jcl = new JarClassLoader();
		jcl.addLoader(functionalDeviceClassLoaderDelegate);
		jcl.add(input);

		return (net.abstractfactory.yunos.driver.Driver) factory.create(jcl,
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

	public Object createFunctionalDeviceProxy(String interfaceClassName,
			DeviceManager deviceManager, String deviceId, int index) {
		Class interfaceClass;
		try {
			interfaceClass = Object.class.forName(interfaceClassName, true,
					functionalDeviceClassLoader);

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
			throw new RuntimeException(
					"functional device interface class not found", e);
		}
		return java.lang.reflect.Proxy.newProxyInstance(
				functionalDeviceClassLoader, new Class[] { interfaceClass,
						DeviceStatusChangeListener.class },
				new FunctionalDeviceProxy(deviceManager, deviceId, index));

	}
}
