package com.driverstack.yunos.driver.device;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractPhysicalDevice implements PhysicalDevice {
	protected List<FunctionalDevice> functionalDevices = new ArrayList<FunctionalDevice>();
	protected Object config;

	@Override
	public void init(Object config) {
		this.config = config;
	}

	@Override
	public Object getConfigure() {

		return config;
	}

	@Override
	public List<FunctionalDevice> getFunctionDevices() {
		return functionalDevices;
	}

	@Override
	public FunctionalDevice getFunctionDevice(int index) {
		return functionalDevices.get(index);
	}
}
