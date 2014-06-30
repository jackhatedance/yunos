package com.deviceyun.smarthome.api.device.transmitter;

import com.deviceyun.smarthome.api.device.Device;

public interface Rf315Transmitter extends Device {
	void transmit(int pulseLength, long code, int bits);
}
