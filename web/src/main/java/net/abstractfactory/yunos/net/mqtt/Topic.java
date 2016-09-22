package net.abstractfactory.yunos.net.mqtt;

public class Topic {

	private MessageType type;
	private String sender;
	private String receiver;
	private String sessionId;

	public Topic(MessageType type, String sender, String receiver,
			String sessionId) {
		this.type = type;
		this.sender = sender;
		this.receiver = receiver;
		this.sessionId = sessionId;
	}

	public static Topic valueOf(String topic) {
		String[] ss = topic.split("/");

		if (ss.length != 6)
			throw new RuntimeException("wrong topic:" + topic);

		String sType = ss[0].toUpperCase();
		MessageType type = MessageType.valueOf(sType);

		String receiver = ss[2];
		String sender = ss[4];

		String sessionId = ss[5];

		return new Topic(type, sender, receiver, sessionId);
	}

	public MessageType getType() {
		return type;
	}

	public void setType(MessageType type) {
		this.type = type;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

}
