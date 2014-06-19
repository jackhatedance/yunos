package com.deviceyun.smarthome.api.v1.device.transmitter;

import com.deviceyun.smarthome.api.v1.device.GenericDeivce;

public interface Rf433Transmitter extends GenericDeivce {
	void transmit(int pulseLength, long code, int bits);
}
