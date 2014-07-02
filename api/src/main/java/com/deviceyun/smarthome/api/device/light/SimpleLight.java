package com.deviceyun.smarthome.api.device.light;

import com.deviceyun.smarthome.api.device.FunctionDevice;

public interface SimpleLight extends FunctionDevice {
	void on();

	void off();
}
