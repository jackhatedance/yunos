package net.abstractfactory.yunos.deviceApi.router;

import java.util.List;

import net.abstractfactory.yunos.driver.device.FunctionalDevice;

public interface WirelessRouter extends FunctionalDevice {

	String getWanIp();

	List<String> getWirelessDevices();
}
