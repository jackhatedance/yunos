package com.deviceyun.yunos;

import com.deviceyun.yunos.engine.RemoteFacade;

/**
 * this represent the system
 * 
 * @author jack
 * 
 */
public class Yun {

	private static DeviceManagerImpl deviceManager;
	private static DriverManager driverManager;
	private static RemoteFacade remoteFacade;
	static {
		deviceManager = new DeviceManagerImpl();
		driverManager = new DriverManagerImpl();

		remoteFacade = new RemoteFacade(deviceManager);
	}

	public static RemoteFacade getRemoteFacade() {
		return remoteFacade;
	}
}
