package com.deviceyun.yunos.api.device._switch;

import com.deviceyun.yunos.api.device.FunctionalDevice;

public interface ElectricitySwitch extends FunctionalDevice {
	void on();

	void off();
}
