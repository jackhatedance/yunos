package com.deviceyun.smarthome.driver;

import java.util.List;

import com.deviceyun.smarthome.api.device.Device;
import com.deviceyun.smarthome.api.device.DeviceInfo;
import com.deviceyun.smarthome.api.driver.ConfigItem;
import com.deviceyun.smarthome.api.driver.SupportedDevice;
/**
 * a driver submit by developers. 
 *
 * @author jackding
 *
 */
public abstract class Driver {

	abstract String getSdkVersion();
	
	abstract List<SupportedDevice> getSupportedDevices();

	abstract List<ConfigItem> getConfigItems();
	
	abstract Device createDevice(DeviceInfo info);
}
