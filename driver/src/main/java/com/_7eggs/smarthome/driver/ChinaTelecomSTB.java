package com._7eggs.smarthome.driver;

import com.deviceyun.smarthome.api.device.AbstractDevice;
import com.deviceyun.smarthome.api.device.transmitter.IrTransmitter;
import com.deviceyun.smarthome.api.device.tv.TV;

public class ChinaTelecomSTB extends AbstractDevice implements TV {
	private IrTransmitter controller;

	private static long CODE_CHANNEL[] = { 0x12345, 0x123456 };

	public ChinaTelecomSTB() {
		configure.put("bits", 24);
		configure.put("repeat", 1);
	}

	public void setController(IrTransmitter controller) {
		this.controller = controller;
	}

	public static void main(String[] args) {

		IrRfTransmitter myTransmiter = new IrRfTransmitter();

		ChinaTelecomSTB tv = new ChinaTelecomSTB();

		tv.setController(myTransmiter);

		tv.switchToChannel(1);

	}

	@Override
	public void on() {
		// TODO Auto-generated method stub

	}

	@Override
	public void off() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setVolume(int vol) {
		// TODO Auto-generated method stub

	}

	@Override
	public void switchToChannel(int channel) {
		controller.transmit(IrTransmitter.TYPE_NEC, CODE_CHANNEL[channel],
				configure.getInt("bits"), configure.getInt("repeat"));

	}
}
