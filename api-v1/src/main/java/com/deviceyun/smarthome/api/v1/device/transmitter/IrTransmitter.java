package com.deviceyun.smarthome.api.v1.device.transmitter;

import com.deviceyun.smarthome.api.v1.device.GenericDeivce;

public interface IrTransmitter extends GenericDeivce {
	void transmit(int type, String command, long code, int bits, int repeat);
}
