package com._7eggs.smartdevice.application;

import java.util.Properties;

public interface Daemon {

	void init(Properties props);

	void start();

	void stop();
}
