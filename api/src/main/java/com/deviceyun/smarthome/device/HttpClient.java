package com.deviceyun.smarthome.device;

import java.util.Map;

public interface HttpClient {
	String get(Map<String, String> parameters);
}