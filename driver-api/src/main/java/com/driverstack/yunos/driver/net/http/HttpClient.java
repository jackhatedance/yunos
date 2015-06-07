package com.driverstack.yunos.driver.net.http;

import java.util.Map;

public interface HttpClient {
	String get(String url,Map<String, String> parameters);
}
