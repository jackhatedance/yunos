package com.deviceyun.smarthome.api.device;

import java.util.List;

public interface CompositeDevice extends Device{

	List<Device> getFunctionDevices();
	Device getFunctionDevice(String name);
}
