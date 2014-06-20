package com.deviceyun.smarthome.api.v1.device.transmitter;

import com.deviceyun.smarthome.api.v1.device.Device;

public interface IrTransmitter extends Device {
	void transmit(int type, String command, long code, int bits, int repeat);
}
