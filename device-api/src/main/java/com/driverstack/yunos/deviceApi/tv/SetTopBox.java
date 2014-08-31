package com.driverstack.yunos.deviceApi.tv;

import com.driverstack.yunos.device.FunctionalDevice;

public interface SetTopBox extends FunctionalDevice {
	// =========manufacturer specification keys begin===================
	static final String SPEC_ABC = "abc";

	// =========functions begin===================

	void on();

	void suspend();

	void setVolume(int vol);

	void liveTv(int channel);

	void recordedTv(String program);

	void movie(String name);

}
