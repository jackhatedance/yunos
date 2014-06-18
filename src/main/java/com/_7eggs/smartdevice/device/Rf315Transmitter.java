package com._7eggs.smartdevice.device;

public interface Rf315Transmitter {
	void transmit(int pulseLength, long code, int bits);
}
