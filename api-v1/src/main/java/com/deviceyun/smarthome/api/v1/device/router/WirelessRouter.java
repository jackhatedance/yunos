package com.deviceyun.smarthome.api.v1.device.router;

import java.util.List;

import com.deviceyun.smarthome.api.v1.device.Device;

public interface WirelessRouter extends Device {

	String getWanIp();

	List<String> getWirelessDevices();
}
