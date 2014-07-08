package com.deviceyun.smarthome;

import com.deviceyun.smarthome.engine.RemoteFacade;

/**
 * this represent the system
 * 
 * @author jack
 * 
 */
public class Yun {

	private static DeviceManager deviceManager;
	private static DriverManager driverManager;
	private static RemoteFacade remoteFacade;
	static {
		deviceManager = new DeviceManager();
		driverManager = new DriverManager();

		remoteFacade = new RemoteFacade(deviceManager);
	}

	public static RemoteFacade getRemoteFacade() {
		return remoteFacade;
	}
}
