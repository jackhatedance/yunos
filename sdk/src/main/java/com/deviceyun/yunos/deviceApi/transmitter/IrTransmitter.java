package com.deviceyun.yunos.deviceApi.transmitter;

import com.deviceyun.yunos.device.FunctionalDevice;

public interface IrTransmitter extends FunctionalDevice {
	static final String TYPE_SHARP = "Sharp";
	static final String TYPE_NEC = "Nec";
	static final String TYPE_SONY = "Sony";

	void transmit(String type, long code, int bits, int repeat);
}
