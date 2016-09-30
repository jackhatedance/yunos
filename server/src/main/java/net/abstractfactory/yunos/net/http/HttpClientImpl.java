package net.abstractfactory.yunos.net.http;

import java.io.IOException;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import net.abstractfactory.yunos.driver.net.http.HttpClient;

public class HttpClientImpl implements HttpClient {
	
	private Logger logger = Logger.getLogger(HttpClientImpl.class);

	@Override
	public String get(String url, Map<String, String> parameters) {
		CloseableHttpClient httpclient = HttpClients.createDefault();

		StringBuilder sb = new StringBuilder();

		int count = 0;
		for (String name : parameters.keySet()) {
			if (count > 0)
				sb.append("&");

			String value = parameters.get(name);
			sb.append(name + "=" + value);
			
			count++;
		}

		String url2 = url + "?" + sb.toString();
		System.out.println("http client get:"+url2);
		try {
			
			HttpGet httpget = new HttpGet(url2);

			// Create a custom response handler
			org.apache.http.client.ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

				public String handleResponse(final HttpResponse response)
						throws ClientProtocolException, IOException {
					int status = response.getStatusLine().getStatusCode();
					if (status >= 200 && status < 300) {
						HttpEntity entity = response.getEntity();
						return entity != null ? EntityUtils.toString(entity)
								: null;
					} else {
						throw new ClientProtocolException(
								"Unexpected response status: " + status);
					}
				}

			};
			String responseBody = httpclient.execute(httpget, responseHandler);

			return responseBody;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {

				throw new RuntimeException(e);
			}
		}
	}
}
