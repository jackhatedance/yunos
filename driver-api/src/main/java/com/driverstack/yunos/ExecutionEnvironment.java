package com.driverstack.yunos;

import com.driverstack.yunos.driver.net.http.HttpClient;

public interface ExecutionEnvironment {

	HttpClient getHttpClient();
	
	MqttService getMqttService();
	
	TimerService getTimerService();
}
