package com.deviceyun.smarthome.driver.generic;

import com.deviceyun.smarthome.api.v1.device.transmitter.IrTransmitter;
import com.deviceyun.smarthome.api.v1.device.transmitter.Rf315Transmitter;
import com.deviceyun.smarthome.api.v1.device.transmitter.Rf433Transmitter;
import com.deviceyun.smarthome.device.HttpClient;

public class IrRfTransmitter implements Rf433Transmitter, Rf315Transmitter,
		IrTransmitter {

	private HttpClient httpClient;

	@Override
	public void transmit(int pulseLength, long code, int bits) {
		System.out.println(String.format("transmit RF433: %d,%d,%d",
				pulseLength, code, bits));

		// httpClient.get(null);

	}

	@Override
	public void transmit(int type, String command, long code, int bits,
			int repeat) {
		System.out.println(String.format("transmit IR: %d,%s,%d,%d", type,
				command, code, bits));

	}

}
