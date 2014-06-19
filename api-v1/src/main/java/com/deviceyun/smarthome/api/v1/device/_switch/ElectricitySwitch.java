package com.deviceyun.smarthome.api.v1.device._switch;

import com.deviceyun.smarthome.api.v1.device.GenericDeivce;

public interface ElectricitySwitch extends GenericDeivce {
	void on();

	void off();
}
