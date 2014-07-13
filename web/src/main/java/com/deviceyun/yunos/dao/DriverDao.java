package com.deviceyun.yunos.dao;

import java.util.List;

import com.deviceyun.yunos.api.device.Model;
import com.deviceyun.yunos.domain.Driver;

public interface DriverDao {

	/**
	 * find all available driver for a model
	 * 
	 * @param model
	 * @return
	 */
	List<Driver> findDriver(Model model);
}
