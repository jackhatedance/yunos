package com._7eggs.smarthome.driver;

import com.deviceyun.smarthome.api.device.AbstractDevice;
import com.deviceyun.smarthome.api.device._switch.ElectricitySwitch;
import com.deviceyun.smarthome.api.device.transmitter.Rf433Transmitter;

public class Rf433SwitchSocket extends AbstractDevice implements
		ElectricitySwitch {
	private Rf433Transmitter controller;

	private int pulseLength = 166;
	private long codeOn = 123;
	private long codeOff = 456;
	private int bits = 24;

	@Override
	public Object operate(String operation, Object[] parameters) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void on() {
		controller.operate("transmit",
				new Object[] { pulseLength, codeOn, bits });
		// controller.transmit(pulseLength, codeOn, bits);
	}

	@Override
	public void off() {
		controller.transmit(pulseLength, codeOff, bits);

	}

	public void setController(Rf433Transmitter controller) {
		this.controller = controller;
	}

}
