package com.deviceyun.yunos.device;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractPhysicalDevice implements PhysicalDevice {
	private List<FunctionalDevice> functionalDevices=new ArrayList<FunctionalDevice>();

	@Override
	public void init() {

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
