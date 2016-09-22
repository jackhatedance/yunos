package net.abstractfactory.yunos;

import java.util.Map;

public interface MqttService {

	public Object call(String clientId, Map<String, String> parameters);

}