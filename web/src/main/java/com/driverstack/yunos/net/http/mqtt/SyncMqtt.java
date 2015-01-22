package com.driverstack.yunos.net.http.mqtt;

import org.eclipse.paho.client.mqttv3.MqttException;

public class SyncMqtt {
	public static void main(String[] args) throws MqttException {
		String brokerUrl = "tcp://10.224.202.59:1883";
		String clientId = "driverstack server";

		Sample1 sample1 = new Sample1(brokerUrl, clientId, true, true, "", "");
		sample1.publish("request/to/12/from/0/5001", 2, "isOn".getBytes());
		sample1.subscribe("response/to/0/#", 2);

	}

}
