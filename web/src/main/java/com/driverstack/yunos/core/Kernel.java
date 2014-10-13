package com.driverstack.yunos.core;

import org.springframework.stereotype.Component;

import com.driverstack.yunos.ExecutionEnvironment;
import com.driverstack.yunos.driver.net.http.HttpClient;
import com.driverstack.yunos.net.http.HttpClientImpl;

@Component
public class Kernel implements ExecutionEnvironment {
	
	@Override
	public HttpClient getHttpClient() {
		
		return new HttpClientImpl();
	}

}
