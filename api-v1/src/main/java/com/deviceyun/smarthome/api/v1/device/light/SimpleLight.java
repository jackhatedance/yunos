package com.deviceyun.smarthome.api.v1.device.light;

import com.deviceyun.smarthome.api.v1.device.GenericDeivce;

public interface SimpleLight extends GenericDeivce {
	void on();

	void off();
}
