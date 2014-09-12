package com.driverstack.yunos.service;

import java.util.List;

import com.driverstack.yunos.domain.Vendor;

public interface DeviceClassService {

	List<com.driverstack.yunos.domain.DeviceClass> find(Vendor vendor,
			String locale);
}
