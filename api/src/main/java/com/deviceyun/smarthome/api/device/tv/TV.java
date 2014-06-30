package com.deviceyun.smarthome.api.device.tv;

import com.deviceyun.smarthome.api.device.Device;

public interface TV extends Device {

	void on();
	void off();
	/**
	 * 
	 * @param vol 0 to 99
	 */
	void setVolume(int vol);
	void switchToChannel(int channel);

}
