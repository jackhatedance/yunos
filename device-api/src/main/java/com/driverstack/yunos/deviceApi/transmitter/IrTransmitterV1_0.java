package com.driverstack.yunos.deviceApi.transmitter;

import com.driverstack.yunos.driver.device.FunctionalDevice;

public interface IrTransmitterV1_0 extends FunctionalDevice {
	
	static final String brand = "Generic";
	static final String category = "Transmitter";
	static final String name = "IrTransmitter";
	static final String version = "1.0";

	static final String TYPE_SHARP = "Sharp";
	static final String TYPE_NEC = "Nec";
	static final String TYPE_SONY = "Sony";

	void transmit(String type, long code, int bits, int repeat);
}
