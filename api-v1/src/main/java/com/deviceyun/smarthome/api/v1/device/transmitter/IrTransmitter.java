package com.deviceyun.smarthome.api.v1.device.transmitter;

public interface IrTransmitter {
	void transmit(int type, String command, long code, int bits, int repeat);
}
