package com.deviceyun.smarthome.api.v1.device.router;

import java.util.List;

import com.deviceyun.smarthome.api.v1.device.GenericDeivce;

public interface WirelessRouter extends GenericDeivce {

	String getWanIp();

	List<String> getWirelessDevices();
}
