package com.deviceyun.yunos;

import com.deviceyun.yunos.api.device.Model;
import com.deviceyun.yunos.api.driver.Driver;

public interface DriverManager {
	Driver findDriver(Model model);
}