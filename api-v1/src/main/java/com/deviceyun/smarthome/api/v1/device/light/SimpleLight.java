package com.deviceyun.smarthome.api.v1.device.light;

import com.deviceyun.smarthome.api.v1.device.Device;

public interface SimpleLight extends Device {
	void on();

	void off();
}
