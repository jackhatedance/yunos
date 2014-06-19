package com.deviecyun.smarthome.application;

import java.util.Properties;

public interface Daemon {

	void init(Properties props);

	void start();

	void stop();
}
