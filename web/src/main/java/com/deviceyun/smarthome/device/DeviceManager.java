package com.deviceyun.smarthome.device;

import java.util.Map;

import com.deviceyun.smarthome.api.device.Device;

/**
 * device manager responsibilities:
 * <ul>
 * <li>maintain device links</li>
 * <li>load/save from/to DB</li>
 * <li>cache devices</li>
 * </ul>
 * 
 * @author jackding
 * 
 */
public class DeviceManager {

	public Device getDevice(String id) {
		return null;
	}

	private Device loadDevice(String id) {
		return null;
	}

	private void saveDevice(String id) {

	}

	public void operateDevice(String apiKey, String deviceId, String operation,
			Map<String, Object> parameters) {

	}
}
