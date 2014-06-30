package com.deviceyun.smarthome.api.device.router;

import java.util.List;

import com.deviceyun.smarthome.api.device.Device;

public interface WirelessRouter extends Device {

	String getWanIp();

	List<String> getWirelessDevices();
}
