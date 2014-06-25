package com.deviceyun.smarthome.api.device.transmitter;

import com.deviceyun.smarthome.api.device.Device;

public interface IrTransmitter extends Device {
	void transmit(int type, String command, long code, int bits, int repeat);
}
