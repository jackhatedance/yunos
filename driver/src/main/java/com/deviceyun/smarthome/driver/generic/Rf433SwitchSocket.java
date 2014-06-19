package com.deviceyun.smarthome.driver.generic;

import com.deviceyun.smarthome.api.v1.device._switch.ElectricitySwitch;
import com.deviceyun.smarthome.api.v1.device.transmitter.Rf433Transmitter;

public class Rf433SwitchSocket implements ElectricitySwitch {
	private Rf433Transmitter controller;

	private int pulseLength = 166;
	private long codeOn = 123;
	private long codeOff = 456;
	private int bits = 24;

	@Override
	public void on() {
		controller.transmit(pulseLength, codeOn, bits);

	}

	@Override
	public void off() {
		controller.transmit(pulseLength, codeOff, bits);

	}

	public void setController(Rf433Transmitter controller) {
		this.controller = controller;
	}

}
