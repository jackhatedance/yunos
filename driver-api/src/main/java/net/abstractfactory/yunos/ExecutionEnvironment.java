package net.abstractfactory.yunos;

import net.abstractfactory.yunos.driver.net.http.HttpClient;

public interface ExecutionEnvironment {

	HttpClient getHttpClient();
	
	MqttService getMqttService();
	
	TimerService getTimerService();
}
