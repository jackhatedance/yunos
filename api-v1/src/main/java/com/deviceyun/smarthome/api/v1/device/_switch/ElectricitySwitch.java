package com.deviceyun.smarthome.api.v1.device._switch;

import com.deviceyun.smarthome.api.v1.device.Device;

public interface ElectricitySwitch extends Device {
	void on();

	void off();
}
