package net.abstractfactory.yunos.deviceApi.light;

import net.abstractfactory.yunos.driver.device.FunctionalDevice;

public interface SimpleLight extends FunctionalDevice{

	void on();
	void off();
}
