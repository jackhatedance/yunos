package com.deviceyun.yunos.deviceApi.router;

import java.util.List;

import com.deviceyun.yunos.device.FunctionalDevice;

public interface WirelessRouter extends FunctionalDevice {

	String getWanIp();

	List<String> getWirelessDevices();
}
