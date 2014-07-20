package com.deviceyun.yunos.device;

import java.util.Map;

public abstract class AbstractPhysicalDevice implements PhysicalDevice {
	private Map<String, FunctionalDevice> functionalDeviceMap;

	@Override
	public void init() {

	}
}
