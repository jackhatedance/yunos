package com._7eggs.smartdevice.driver.generic;

import com._7eggs.smartdevice.device.HttpClient;
import com._7eggs.smartdevice.device.IrTransmitter;
import com._7eggs.smartdevice.device.Rf315Transmitter;
import com._7eggs.smartdevice.device.Rf433Transmitter;

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
