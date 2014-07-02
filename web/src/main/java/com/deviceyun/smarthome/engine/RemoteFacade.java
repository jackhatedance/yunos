package com.deviceyun.smarthome.engine;

import java.util.Map;

import com.deviceyun.smarthome.DeviceManager;
import com.deviceyun.smarthome.api.device.FunctionDevice;

public class RemoteFacade {
	public static final String API_KEY = "apiKey";
	public static final String DEVICE_ID = "deviceId";
	public static final String OPERATION = "operation";

	DeviceManager deviceManager = null;

	public Object urlApi(Map<String, String> parameters) {
		// String apiKey = parameters.get(API_KEY);

		String deviceId = parameters.get(DEVICE_ID);
		String operation = parameters.get(OPERATION);

		FunctionDevice device = deviceManager.getDevice(deviceId);
		// perform operation on device
		Object ret = device.invoke(operation, null);
		return ret;
	}
}
