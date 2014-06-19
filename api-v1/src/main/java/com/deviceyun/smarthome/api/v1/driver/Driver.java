package com.deviceyun.smarthome.api.v1.driver;

import java.util.List;

import com.deviceyun.smarthome.api.v1.device.GenericDeivce;

public abstract class Driver {

	abstract List<SupportedDevice> getSupportedDevice();

	abstract List<ConfigItem> getConfigItems();

	/**
	 * a composite device may contains multiple virtual(sub) devices;
	 * 
	 * @return
	 */
	abstract List<GenericDeivce> getDeviceObjects();
}
