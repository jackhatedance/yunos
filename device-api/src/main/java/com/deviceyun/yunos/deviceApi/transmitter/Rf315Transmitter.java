package com.deviceyun.yunos.deviceApi.transmitter;

import com.deviceyun.yunos.device.FunctionalDevice;

public interface Rf315Transmitter extends FunctionalDevice {
	void transmit(int pulseLength, long code, int bits);
}
