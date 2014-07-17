package com.deviceyun.yunos.api.device.tv;

import com.deviceyun.yunos.api.device.FunctionalDevice;

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
