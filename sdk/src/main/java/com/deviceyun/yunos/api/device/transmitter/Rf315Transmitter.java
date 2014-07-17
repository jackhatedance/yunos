package com.deviceyun.yunos.api.device.transmitter;

import com.deviceyun.yunos.api.device.FunctionalDevice;

public interface Rf315Transmitter extends FunctionalDevice {
	void transmit(int pulseLength, long code, int bits);
}
