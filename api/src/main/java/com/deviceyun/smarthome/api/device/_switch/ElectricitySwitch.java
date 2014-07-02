package com.deviceyun.smarthome.api.device._switch;

import com.deviceyun.smarthome.api.device.FunctionDevice;

public interface ElectricitySwitch extends FunctionDevice {
	void on();

	void off();
}
