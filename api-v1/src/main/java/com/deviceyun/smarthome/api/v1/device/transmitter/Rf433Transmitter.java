package com.deviceyun.smarthome.api.v1.device.transmitter;

public interface Rf433Transmitter {
	void transmit(int pulseLength, long code, int bits);
}
