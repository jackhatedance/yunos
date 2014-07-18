package com.deviceyun.yunos.dao;

import java.util.List;

import com.deviceyun.yunos.device.Model;
import com.deviceyun.yunos.domain.Driver;

public interface DriverDao {
	Driver get(String id);
	/**
	 * find all available driver for a model
	 * 
	 * @param model
	 * @return
	 */
	List<Driver> findDriver(Model model);
}
