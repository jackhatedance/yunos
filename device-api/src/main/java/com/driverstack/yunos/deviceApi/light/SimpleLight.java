package com.driverstack.yunos.deviceApi.light;

import com.driverstack.yunos.driver.device.FunctionalDevice;

public interface SimpleLight extends FunctionalDevice{

	void on();
	void off();
}
