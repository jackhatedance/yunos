package com.deviceyun.smarthome.api.device._switch;

import com.deviceyun.smarthome.api.device.Device;

public interface ElectricitySwitch extends Device {
	void on();

	void off();
}
