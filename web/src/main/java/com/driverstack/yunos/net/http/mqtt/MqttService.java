package com.driverstack.yunos.net.http.mqtt;

import java.util.concurrent.Future;

public interface MqttService {

	String syncCall(String deviceId, String message);
	Future<String> asyncCall(String deviceId, String message);
	
}
