package com.driverstack.yunos.deviceApi.transmitter;

import com.driverstack.yunos.device.FunctionalDevice;

public interface RfTransmitterV1_0 extends FunctionalDevice {
	static final String brand = "Generic";
	static final String category = "Transmitter";
	static final String name = "RfTransmitter";
	static final String version = "1.0";

	static final int FREQUENCY_315 = 315;
	static final int FREQUENCY_433 = 433;

	void transmit(int frequency, int pulseLength, long code, int bits);
}
