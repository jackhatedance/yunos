package com.deviceyun.smarthome.driver.generic;

import com.deviceyun.smarthome.api.v1.device.transmitter.IrTransmitter;
import com.deviceyun.smarthome.api.v1.device.tv.TV;

public class ChinaTelecomSTB implements TV {
	private IrTransmitter controller;

	@Override
	public void setChannel(int channel) {
		controller.transmit(0, "abc", 2, 3, 1);

	}

	public void setController(IrTransmitter controller) {
		this.controller = controller;
	}

	public static void main(String[] args) {
		IrRfTransmitter myTransmiter = new IrRfTransmitter();

		ChinaTelecomSTB tv = new ChinaTelecomSTB();

		tv.setController(myTransmiter);

		tv.setChannel(1);

	}
}
