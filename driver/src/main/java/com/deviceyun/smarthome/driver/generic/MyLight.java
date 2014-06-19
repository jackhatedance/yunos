package com.deviceyun.smarthome.driver.generic;

import com.deviceyun.smarthome.api.v1.device._switch.ElectricitySwitch;
import com.deviceyun.smarthome.api.v1.device.light.SimpleLight;

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
