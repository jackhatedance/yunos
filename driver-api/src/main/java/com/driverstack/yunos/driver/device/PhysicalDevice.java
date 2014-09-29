package com.driverstack.yunos.driver.device;

import java.util.List;

/**
 * it is physical device.
 * 
 * @author jack
 * 
 */
public interface PhysicalDevice {

	void init(Object configure);

	Object getConfigure();

	List<FunctionalDevice> getFunctionDevices();

	FunctionalDevice getFunctionDevice(int index);
}
