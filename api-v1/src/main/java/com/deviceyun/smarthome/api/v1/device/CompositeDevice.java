package com.deviceyun.smarthome.api.v1.device;

import java.util.List;

public interface CompositeDevice extends Device{

	List<Device> getSubDevices();
	Device getSubDevice(String name);
}
