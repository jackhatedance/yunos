package com.driverstack.yunos.device.utils;

import java.util.Map;

public interface HttpClient {
	String get(Map<String, String> parameters);
}
