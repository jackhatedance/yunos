package com.deviceyun.smarthome.api.device.tv;

import com.deviceyun.smarthome.api.device.FunctionDevice;

public interface TV extends FunctionDevice {

	void on();
	void off();
	/**
	 * 
	 * @param vol 0 to 99
	 */
	void setVolume(int vol);
	void switchToChannel(int channel);

}
