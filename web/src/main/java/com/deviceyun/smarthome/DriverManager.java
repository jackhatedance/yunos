package com.deviceyun.smarthome;

import com.deviceyun.smarthome.api.device.Model;
import com.deviceyun.smarthome.api.driver.Driver;

public interface DriverManager {
	Driver findDriver(Model model);
}