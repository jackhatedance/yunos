package com._7eggs.smartdevice.driver.generic;

import com._7eggs.smartdevice.device.ElectricitySwitch;
import com._7eggs.smartdevice.device.SimpleLight;

public class MyLight implements SimpleLight {
	ElectricitySwitch controller;

	@Override
	public void on() {
		controller.on();

	}

	@Override
	public void off() {
		controller.off();

	}

	public void setController(ElectricitySwitch controller) {
		this.controller = controller;
	}

	public static void main(String[] args) {
		IrRfTransmitter myTransmiter = new IrRfTransmitter();
		Rf433SwitchSocket mySocket = new Rf433SwitchSocket();
		MyLight light = new MyLight();

		light.setController(mySocket);
		mySocket.setController(myTransmiter);

		SimpleLight sl = light;
		sl.on();
		sl.off();

	}
}
