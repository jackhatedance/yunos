package com.driverstack.yunos.net.http.mqtt;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence;

/**
 * maintain one connection to MQ server. turn async call to sync call.
 * 
 * @author jackding
 *
 */
public class MqttServiceImpl implements MqttService, MqttCallback {
	org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(MqttServiceImpl.class);

	private MqttAsyncClient client;
	String brokerUrl = "tcp://10.224.202.59:1883";
	String clientId = "JavaSample";
	private MqttConnectOptions conOpt;

	Map<String, MqttTaskFuture> pendingSessions = new HashMap<String, MqttTaskFuture>();

	public MqttServiceImpl() {
		try {
			createClient(brokerUrl, clientId, false, true, null, null);
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void createClient(String brokerUrl, String clientId,
			boolean cleanSession, boolean quietMode, String userName,
			String password) throws MqttException {

		String tmpDir = System.getProperty("java.io.tmpdir");
		MqttDefaultFilePersistence dataStore = new MqttDefaultFilePersistence(
				tmpDir);

		try {
			// Construct the connection options object that contains connection
			// parameters
			// such as cleanSession and LWT
			conOpt = new MqttConnectOptions();
			conOpt.setCleanSession(cleanSession);
			if (password != null) {
				conOpt.setPassword(password.toCharArray());
			}
			if (userName != null) {
				conOpt.setUserName(userName);
			}

			// Construct a non-blocking MQTT client instance
			client = new MqttAsyncClient(this.brokerUrl, clientId, dataStore);

			// Set this wrapper as the callback handler
			client.setCallback(this);

		} catch (MqttException e) {
			e.printStackTrace();
			// log("Unable to set up client: " + e.toString());
			System.exit(1);
		}
	}

	@Override
	public Future<String> asyncCall(String deviceId, String message) {
		String sessionId = "";// random
		try {
			publish(deviceId, message, sessionId);

			String subTopic = String.format("+/to/yunos/#");
			subscribe(subTopic, 2);
			MqttTaskFuture future = new MqttTaskFuture();
			pendingSessions.put(sessionId, future);
			return future;
		} catch (Exception e) {

			throw new RuntimeException(e);
		}
	}

	@Override
	public String syncCall(String deviceId, String message) {
		try {
			Future<String> f = asyncCall(deviceId, message);
			return f.get(10, TimeUnit.SECONDS);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	protected void publish(String deviceId, String message, String sessionId)
			throws MqttException {

		String topic = String.format("request/to/%s/from/%s/%s", deviceId,
				"yunos", sessionId);

		publish(topic, 2, message.getBytes());
	}

	private void log(String msg) {
		logger.info(msg);
	}

	public void publish(String topicName, int qos, byte[] payload)
			throws MqttException {

		// Connect to the MQTT server
		// issue a non-blocking connect and then use the token to wait until the
		// connect completes. An exception is thrown if connect fails.
		log("Connecting to " + brokerUrl + " with client ID "
				+ client.getClientId());
		IMqttToken conToken = client.connect(conOpt, null, null);
		conToken.waitForCompletion();
		log("Connected");

		String time = new Timestamp(System.currentTimeMillis()).toString();
		log("Publishing at: " + time + " to topic \"" + topicName + "\" qos "
				+ qos);

		// Construct the message to send
		MqttMessage message = new MqttMessage(payload);
		message.setQos(qos);

		// Send the message to the server, control is returned as soon
		// as the MQTT client has accepted to deliver the message.
		// Use the delivery token to wait until the message has been
		// delivered
		IMqttDeliveryToken pubToken = client.publish(topicName, message, null,
				null);
		pubToken.waitForCompletion();
		log("Published");

		// Disconnect the client
		// Issue the disconnect and then use a token to wait until
		// the disconnect completes.
		log("Disconnecting");
		IMqttToken discToken = client.disconnect(null, null);
		discToken.waitForCompletion();
		log("Disconnected");
	}

	public void subscribe(String topicName, int qos) throws MqttException {

		// Connect to the MQTT server
		// issue a non-blocking connect and then use the token to wait until the
		// connect completes. An exception is thrown if connect fails.
		log("Connecting to " + brokerUrl + " with client ID "
				+ client.getClientId());
		IMqttToken conToken = client.connect(conOpt, null, null);
		conToken.waitForCompletion();
		log("Connected");

		// Subscribe to the requested topic.
		// Control is returned as soon client has accepted to deliver the
		// subscription.
		// Use a token to wait until the subscription is in place.
		log("Subscribing to topic \"" + topicName + "\" qos " + qos);

		IMqttToken subToken = client.subscribe(topicName, qos, null, null);
		subToken.waitForCompletion();
		log("Subscribed to topic \"" + topicName);

		// Continue waiting for messages until the Enter is pressed
		/*
		log("Press <Enter> to exit");
		try {
			System.in.read();
		} catch (IOException e) {
			// If we can't read we'll just exit
		}
*/ 
	}

	@Override
	public void connectionLost(Throwable arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void messageArrived(String topic, MqttMessage message)
			throws Exception {
		// find future and notify

		logger.info("message arrive:"+topic);
		
		String sessionId = "";

		MqttTaskFuture future = pendingSessions.get(sessionId);
		if (future != null) {
			future.onResult(new String(message.getPayload()));
			pendingSessions.remove(future);
		} else {
			logger.warn("invalid session id:" + sessionId);
			//throw new RuntimeException("invalid session id:" + sessionId);
		}

	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken arg0) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		MqttService mqttService = new MqttServiceImpl();

		String isOn = mqttService.syncCall("123", "isOn");

		System.out.println("result:" + isOn);
		
		
	}
}
