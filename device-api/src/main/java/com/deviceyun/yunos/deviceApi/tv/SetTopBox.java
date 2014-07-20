package com.deviceyun.yunos.deviceApi.tv;

import com.deviceyun.yunos.device.FunctionalDevice;

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
