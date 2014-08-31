package com.driverstack.yunos.deviceApi.router;

import java.util.List;

import com.driverstack.yunos.device.FunctionalDevice;

public interface WirelessRouter extends FunctionalDevice {

	String getWanIp();

	List<String> getWirelessDevices();
}
