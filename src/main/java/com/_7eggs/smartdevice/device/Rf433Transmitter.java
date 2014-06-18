package com._7eggs.smartdevice.device;

public interface Rf433Transmitter {
	void transmit(int pulseLength, long code, int bits);
}
