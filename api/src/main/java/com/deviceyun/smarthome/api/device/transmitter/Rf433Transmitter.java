package com.deviceyun.smarthome.api.device.transmitter;

import com.deviceyun.smarthome.api.device.FunctionDevice;

public interface Rf433Transmitter extends FunctionDevice {
	void transmit(int pulseLength, long code, int bits);
}
