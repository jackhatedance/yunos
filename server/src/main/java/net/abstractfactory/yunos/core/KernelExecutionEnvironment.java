package net.abstractfactory.yunos.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.abstractfactory.yunos.ExecutionEnvironment;
import net.abstractfactory.yunos.MqttService;
import net.abstractfactory.yunos.TimerService;
import net.abstractfactory.yunos.driver.net.http.HttpClient;
import net.abstractfactory.yunos.net.http.HttpClientImpl;

@Component
public class KernelExecutionEnvironment implements ExecutionEnvironment {
	@Autowired
	private TimerService timerService;

	@Autowired
	private MqttService mqttService;

	@Override
	public HttpClient getHttpClient() {

		return new HttpClientImpl();
	}

	@Override
	public TimerService getTimerService() {
		return this.timerService;
	}

	@Override
	public MqttService getMqttService() {

		return mqttService;
	}

}
