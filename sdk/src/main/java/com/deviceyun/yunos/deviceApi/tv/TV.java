package com.deviceyun.yunos.deviceApi.tv;

import com.deviceyun.yunos.device.FunctionalDevice;

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
