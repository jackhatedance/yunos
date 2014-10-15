package com.driverstack.yunos.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.driverstack.yunos.ExecutionEnvironment;
import com.driverstack.yunos.TimerService;
import com.driverstack.yunos.driver.net.http.HttpClient;
import com.driverstack.yunos.net.http.HttpClientImpl;

@Component
public class KernelExecutionEnvironment implements ExecutionEnvironment {
	@Autowired
	private TimerService timerService;

	@Override
	public HttpClient getHttpClient() {

		return new HttpClientImpl();
	}

	@Override
	public TimerService getTimerService() {
		return this.timerService;
	}
}
