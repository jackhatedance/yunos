package net.abstractfactory.yunos.net.mqtt;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.RandomStringUtils;
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
public class MqttPubSubClient implements MqttCallback {
	org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(MqttPubSubClient.class);
	private MqttAsyncClient client;

	private String brokerUrl ;
	private String clientId = "yunos";

	private static final String SUB_TOPIC = String.format("ds/+/to/yunos/#");
	private static final String REQUEST_TOPIC_FORMAT = "ds/request/to/%s/from/%s/%s";
	
	private static final String WILL_TOPIC = "ds/will/to/all/from/yunos/1";
	private static final String WILL_MESSAGE = "offline";
	
	private MqttConnectOptions conOpt;

	// milliseconds
	private int timeout = 10000;

	Map<String, MqttTaskFuture> pendingSessions = new ConcurrentHashMap<String, MqttTaskFuture>();

	public MqttPubSubClient(String brokerUrl) {
		this.brokerUrl = brokerUrl;
		try {
			createClient(this.brokerUrl, clientId, false, true, null, null);
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}

	public void connect() {
		try {
			log("Connecting to " + brokerUrl + " with client ID "
					+ client.getClientId());
			IMqttToken conToken = client.connect(conOpt, null, null);
			conToken.waitForCompletion();
			log("Connected");

			subscribe(SUB_TOPIC, 2);
		} catch (MqttException e) {
			throw new RuntimeException(e);
		}
	}

	public void disconnect() {
		try {
			log("Disconnecting");
			IMqttToken discToken = client.disconnect(null, null);
			discToken.waitForCompletion();
			log("Disconnected");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected void createClient(String brokerUrl, String clientId,
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

			conOpt.setWill(WILL_TOPIC, WILL_MESSAGE.getBytes(), 2, false);

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

	private String getNextSessionId() {
		return RandomStringUtils.randomAlphanumeric(10);
	}

	public Future<String> asyncCall(String deviceId, String message) {
		String sessionId = getNextSessionId();

		try {
			if (!client.isConnected())
				client.connect();

			publish(deviceId, message, sessionId);

			MqttTaskFuture future = new MqttTaskFuture();
			future.setSessionId(sessionId);

			pendingSessions.put(sessionId, future);
			return future;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public String syncCall(String deviceId, String message) {
		try {
			Future<String> f = asyncCall(deviceId, message);
			String result = f.get(timeout, TimeUnit.MILLISECONDS);

			MqttTaskFuture mtf = (MqttTaskFuture) f;
			pendingSessions.remove(mtf.getSessionId());

			return result;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected void publish(String deviceId, String message, String sessionId)
			throws MqttException {

		String topic = String.format(REQUEST_TOPIC_FORMAT, deviceId,
				clientId, sessionId);

		publish(topic, 2, message.getBytes());
	}

	private void log(String msg) {
		logger.info(msg);
	}

	protected void publish(String topicName, int qos, byte[] payload)
			throws MqttException {

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

	}

	protected void subscribe(String topicName, int qos) throws MqttException {

		log("Subscribing to topic \"" + topicName + "\" qos " + qos);

		IMqttToken subToken = client.subscribe(topicName, qos, null, null);
		subToken.waitForCompletion();
		log("Subscribed to topic \"" + topicName);

	}

	@Override
	public void connectionLost(Throwable arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void messageArrived(String topic, MqttMessage message)
			throws Exception {
		// find future and notify

		logger.info("message arrive:" + topic);

		Topic topicObj = Topic.valueOf(topic);

		if (topicObj.getType() == MessageType.RESPONSE) {

			String sessionId = topicObj.getSessionId();

			MqttTaskFuture future = pendingSessions.get(sessionId);
			if (future != null) {
				future.onResult(new String(message.getPayload()));
				pendingSessions.remove(sessionId);
			} else {
				logger.debug("invalid session id:" + sessionId);
				// throw new RuntimeException("invalid session id:" +
				// sessionId);
			}
		} else if (topicObj.getType() == MessageType.REQUEST) {

		} else {
			// WILL

			logger.info(String.format("device '%s:%s", topicObj.getSender(),
					new String(message.getPayload())));

		}
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken arg0) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) throws IOException {
		String url = "tcp://snapshot-abstractfactory-net.dingjianghao.home:1883";
		MqttPubSubClient mqttService = new MqttPubSubClient(url);
		mqttService.connect();

		try {
			String isOn = mqttService.syncCall("123", "cmd=isOn");
			System.out.println("result:" + isOn);
		} catch (Exception e) {
			System.out.println("error sync call:" + e.getMessage());
		}

		mqttService.disconnect();
	}
}
