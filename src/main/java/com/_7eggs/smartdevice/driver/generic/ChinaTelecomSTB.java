package com._7eggs.smartdevice.driver.generic;

import com._7eggs.smartdevice.device.IrTransmitter;
import com._7eggs.smartdevice.device.TV;

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
