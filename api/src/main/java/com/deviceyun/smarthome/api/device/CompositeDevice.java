package com.deviceyun.smarthome.api.device;

import java.util.List;

public interface CompositeDevice extends FunctionDevice{

	List<FunctionDevice> getFunctionDevices();
	FunctionDevice getFunctionDevice(String name);
}
