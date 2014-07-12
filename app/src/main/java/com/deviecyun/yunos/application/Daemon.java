package com.deviecyun.yunos.application;

import java.util.Properties;

public interface Daemon {

	void init(Properties props);

	void start();

	void stop();
}
