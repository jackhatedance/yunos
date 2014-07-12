package com._7eggs.smarthome.driver;

import com.deviceyun.yunos.api.device.AbstractDevice;
import com.deviceyun.yunos.api.device.DeviceApi;
import com.deviceyun.yunos.api.device._switch.ElectricitySwitch;
import com.deviceyun.yunos.api.device.light.SimpleLight;

public class MyLight extends AbstractDevice implements SimpleLight {
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

	@Override
	public DeviceApi getApi() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getApiVersion() {
		// TODO Auto-generated method stub
		return null;
	}

}
