package com.driverstack.yunos.driver.device.utils;

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

		System.out.print(String.format("GET http://%s:%d/", host, port));

		int i = 0;
		for (String name : parameters.keySet()) {
			if (i > 0)
				System.out.print("&");

			String value = parameters.get(name);
			System.out.print(name + "=" + value);

			i++;
		}
		System.out.println("");
		return "OK";
	}

}
