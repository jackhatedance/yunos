package com.deviceyun.yunos.api.device.light;

import com.deviceyun.yunos.api.device.FunctionalDevice;

public interface SimpleLight extends FunctionalDevice {
	void on();

	void off();
}
