package com._7eggs.smartdevice.device;

public interface IrTransmitter {
	void transmit(int type, String command, long code, int bits, int repeat);
}
