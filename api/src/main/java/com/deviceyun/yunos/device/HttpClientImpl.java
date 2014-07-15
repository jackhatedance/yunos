package com.deviceyun.yunos.device;

import java.util.Map;

public class HttpClientImpl implements HttpClient {

	private String host;
	private int port = 80;

	public HttpClientImpl(String host, int port) {
		this.host = host;
		this.port = port;
	}

	@Override
	public String get(Map<String, String> parameters) {

		System.out.print("HttpClientImpl:");
		for (String name : parameters.keySet()) {
			String value = parameters.get(name);
			System.out.print(name + "=" + value);
			System.out.print("&");
		}

		return "OK";
	}

}
