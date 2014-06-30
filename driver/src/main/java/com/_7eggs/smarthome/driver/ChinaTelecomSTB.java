package com._7eggs.smarthome.driver;

import com.deviceyun.smarthome.api.device.AbstractDevice;
import com.deviceyun.smarthome.api.device.DeviceProxy;
import com.deviceyun.smarthome.api.device.transmitter.IrTransmitter;
import com.deviceyun.smarthome.api.device.transmitter.Rf433Transmitter;
import com.deviceyun.smarthome.api.device.tv.TV;

public class ChinaTelecomSTB extends AbstractDevice implements TV {
	private IrTransmitter controller;

	public void setController(IrTransmitter controller) {
		this.controller = controller;
	}

	public static void main(String[] args) {
		
		IrRfTransmitter myTransmiter = new IrRfTransmitter();
		IrTransmitter myTransmiterProxy = (IrTransmitter)DeviceProxy.newInstance(myTransmiter);

		ChinaTelecomSTB tv = new ChinaTelecomSTB();

		tv.setController(myTransmiterProxy);

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
		// TODO Auto-generated method stub

	}
}
