package com.driverstack.yunos.net.http.mqtt;

import java.io.IOException;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
/**
 * it is an (Arduino) device simulator, can responds to mqtt request.
 *  
 * @author jack
 *
 */
public class DeviceAsyncSimulator implements MqttCallback {
	private String brokerUrl;
	private String clientId = "123";
	private String topic = "request/to/123/from/yunos/+";
	MqttAsyncClient client;

	public DeviceAsyncSimulator(String brokerUrl) {
		this.brokerUrl = brokerUrl;
		try {
			client = new MqttAsyncClient(brokerUrl, clientId);

			// Set this wrapper as the callback handler
			client.setCallback(this);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void connect() {
		try {
			IMqttToken token = client.connect();
			token.waitForCompletion();
			
			token = client.subscribe(topic, 2);
			token.waitForCompletion();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void disconnect() {
		try {
			IMqttToken token = client.disconnect();
			token.waitForCompletion();
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}

	private String extractSessionId(String topic) {
		// response/to/clientid/from/other-id/session-id
		String[] segments = topic.split("/");
		String sessionId = segments[5];

		return sessionId;
	}

	@Override
	public void connectionLost(Throwable arg0) {
		System.out.println("lost connection");

	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken arg0) {
		System.out.println("deliveryComplete");

	}

	@Override
	public void messageArrived(String topic, MqttMessage msg) throws Exception {
		System.out.println("messageArrived:"+topic+" m:"+new String(msg.getPayload()));
		String sessionId = extractSessionId(topic);

		String pubTopic = String.format("response/to/yunos/from/123/%s",
				sessionId);
		publish(pubTopic, "true".getBytes());
	}

	private void publish(String topic, byte[] payload) {
		MqttMessage message = new MqttMessage(payload);
		message.setQos(1);

		try {
			client.publish(topic, message);
			System.out.println("published response");
		} catch (MqttPersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws IOException {
		DeviceAsyncSimulator sim = new DeviceAsyncSimulator(
				"tcp://snapshot-driverstack-com.dingjianghao.home:1883");
		sim.connect();

		System.out.println("press Entry to abort.");
		System.in.read();
		sim.disconnect();

	}
}
