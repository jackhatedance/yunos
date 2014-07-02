package com.deviceyun.smarthome.api.device.router;

import java.util.List;

import com.deviceyun.smarthome.api.device.FunctionDevice;

public interface WirelessRouter extends FunctionDevice {

	String getWanIp();

	List<String> getWirelessDevices();
}
