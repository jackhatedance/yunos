package net.abstractfactory.yunos.driver;

import java.util.List;

import net.abstractfactory.yunos.ExecutionEnvironment;
import net.abstractfactory.yunos.driver.device.DeviceInfo;
import net.abstractfactory.yunos.driver.device.FunctionalDevice;
import net.abstractfactory.yunos.driver.device.Model;
import net.abstractfactory.yunos.driver.device.PhysicalDevice;

/**
 * a driver submit by developers.
 * 
 * @author jackding
 * 
 */
public interface Driver {
	
/**
	 * be unique, usually is a domain name, such as com.example
	 * 
	 * @return
	 */

	String getOrganizationId();

	/**
	 * a unique name within the organization.
	 * 
	 * @return
	 */
	String getArtifactId();

	/**
	 * version of the driver
	 * 
	 * @return
	 */
	String getVersion();

	/**
	 * the version of SDK
	 * 
	 * @return
	 */
	String getSdkVersion();

	List<Model> getSupportedModels();

	/**
	 * the configure class is used to collect configure items by its annotations
	 * 
	 * @return
	 */
	Class getConfigureClass();

	/**
	 * install driver for the device.
	 * 
	 * some initialization work may need be done here.
	 */
	void install(DeviceInfo info);

	/**
	 * uninstall driver
	 * 
	 * @param info
	 */
	void uninstall(DeviceInfo info);

	/**
	 * create device object.
	 * 
	 * @param info
	 * @return
	 */
	PhysicalDevice createDevice(ExecutionEnvironment executionEnvironment,
			DeviceInfo info);
}
