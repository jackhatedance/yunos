package com.deviceyun.yunos.core;

import com.deviceyun.yunos.api.device.FunctionalDevice;

public interface DeviceManager {

	/**
	 * get functional device object(FDO)
	 * 
	 * @param id
	 * @return
	 */
	FunctionalDevice getFunctionDeviceObject(String id);
}