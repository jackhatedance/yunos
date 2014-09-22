package com.driverstack.yunos.deviceApi._switch;

import com.driverstack.yunos.driver.device.FunctionalDevice;

public interface ElectricitySwitch extends FunctionalDevice{
	void on();
	void off();
}
