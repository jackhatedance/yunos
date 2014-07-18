package com.deviceyun.yunos.core;

import com.deviceyun.yunos.device.Model;
import com.deviceyun.yunos.driver.Driver;
import com.deviceyun.yunos.domain.Device;

public interface DriverManager {
	/**
	 * find best device driver for model
	 * 
	 * @param model
	 * @return
	 */
	Driver findDriver(Model model);

	/**
	 * load device driver object
	 * 
	 * @param deviceEntity
	 * @return
	 */
	Driver loadDriver(Device deviceEntity);

}