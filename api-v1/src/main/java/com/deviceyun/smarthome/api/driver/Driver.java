package com.deviceyun.smarthome.api.driver;

import java.util.List;

import com.deviceyun.smarthome.api.device.Device;
import com.deviceyun.smarthome.api.device.DeviceInfo;
/**
 * a driver submit by developers. 
 *
 * @author jackding
 *
 */
public abstract class Driver {

	abstract List<SupportedDevice> getSupportedDevices();

	abstract List<ConfigItem> getConfigItems();
	
	abstract Device createDevice(DeviceInfo info);
}
