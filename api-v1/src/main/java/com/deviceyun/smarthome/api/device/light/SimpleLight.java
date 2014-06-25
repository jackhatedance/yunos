package com.deviceyun.smarthome.api.device.light;

import com.deviceyun.smarthome.api.device.Device;

public interface SimpleLight extends Device {
	void on();

	void off();
}
