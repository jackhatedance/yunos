package com.driverstack.yunos.core;

import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.driverstack.yunos.MqttService;
import com.driverstack.yunos.net.mqtt.MqttPubSubClient;

@Component
public class MqttServiceImpl implements MqttService {
	static Logger logger = Logger.getLogger(MqttServiceImpl.class);

	@Value("${mqtt.brokerUrl}")
	private String brokerUrl;

	private MqttPubSubClient mqttPubSubClient;

	public MqttServiceImpl() {

	}

	private MqttPubSubClient getMqttPubSubClient() {
		if (mqttPubSubClient == null) {
			mqttPubSubClient = new MqttPubSubClient(brokerUrl);
			mqttPubSubClient.connect();
		}
		return mqttPubSubClient;
	}

	@Override
	public synchronized Object call(String clientId,
			Map<String, String> parameters) {
		StringBuilder sb = new StringBuilder();

		int i = 0;
		for (String key : parameters.keySet()) {
			if (i > 0)
				sb.append("&");

			String val = parameters.get(key);

			sb.append(key + "=" + val);

			i++;
		}

		return getMqttPubSubClient().syncCall(clientId, sb.toString());
	}

}
