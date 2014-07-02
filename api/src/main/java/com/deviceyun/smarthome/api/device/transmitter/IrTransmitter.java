package com.deviceyun.smarthome.api.device.transmitter;

import com.deviceyun.smarthome.api.device.FunctionDevice;

public interface IrTransmitter extends FunctionDevice {
	static final String TYPE_SHARP = "Sharp";
	static final String TYPE_NEC = "Nec";
	static final String TYPE_SONY = "Sony";

	void transmit(String type, long code, int bits, int repeat);
}
