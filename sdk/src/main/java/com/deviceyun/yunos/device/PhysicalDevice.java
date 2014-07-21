package com.deviceyun.yunos.device;

import java.util.List;

/**
 * it is physical device.
 * 
 * @author jack
 * 
 */
public interface PhysicalDevice {

	Object getConfigure();

	void setConfigure(Object configure);

	void init();


	List<FunctionalDevice> getFunctionDevices();
	FunctionalDevice getFunctionDevice(int index);
}
