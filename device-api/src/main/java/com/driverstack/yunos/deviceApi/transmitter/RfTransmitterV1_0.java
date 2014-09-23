package com.driverstack.yunos.deviceApi.transmitter;

import com.driverstack.yunos.driver.device.FunctionalDevice;

public interface RfTransmitterV1_0 extends FunctionalDevice {
	static final String ORGANIZATION_ID = "Generic";
	static final String ARTIFACT_ID = "RfTransmitter";
	
	
	static final int FREQUENCY_315 = 315;
	static final int FREQUENCY_433 = 433;

	void transmit(int frequency, int pulseLength, long code, int bits);
}
