package com.deviceyun.yunos.api.device.tv;

import com.deviceyun.yunos.api.device.FunctionalDevice;

public interface TV extends FunctionalDevice {

	void on();
	void off();
	/**
	 * 
	 * @param vol 0 to 99
	 */
	void setVolume(int vol);
	void switchToChannel(int channel);

}
