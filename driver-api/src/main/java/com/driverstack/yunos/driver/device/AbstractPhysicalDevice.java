package com.driverstack.yunos.driver.device;

import java.util.ArrayList;
import java.util.List;

import com.driverstack.yunos.ExecutionEnvironment;

public abstract class AbstractPhysicalDevice implements PhysicalDevice {
	protected List<FunctionalDevice> functionalDevices = new ArrayList<FunctionalDevice>();

	protected ExecutionEnvironment executionEnvironment;
	protected Object config;

	@Override
	public void init(ExecutionEnvironment executionEnvironment, Object config) {
		this.executionEnvironment = executionEnvironment;
		this.config = config;
	}

	@Override
	public void destroy() {
	
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
