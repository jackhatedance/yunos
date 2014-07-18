package com.deviceyun.yunos.device;

import java.util.List;

public interface CompositeDevice extends FunctionalDevice{

	List<FunctionalDevice> getFunctionDevices();
	FunctionalDevice getFunctionDevice(String name);
}
