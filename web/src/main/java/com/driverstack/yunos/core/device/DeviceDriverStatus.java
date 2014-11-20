package com.driverstack.yunos.core.device;

public enum DeviceDriverStatus {
	NOT_SET,
	/**
	 * set but not loaded yet, may because of lazy loading.
	 */
	UNLOADED,
	/**
	 * set and loaded
	 */
	LOADED;
}
