package com.deviceyun.yunos.api.device.router;

import java.util.List;

import com.deviceyun.yunos.api.device.FunctionalDevice;

public interface WirelessRouter extends FunctionalDevice {

	String getWanIp();

	List<String> getWirelessDevices();
}
