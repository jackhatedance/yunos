package com.driverstack.yunos.service;

import java.util.List;

import com.driverstack.yunos.domain.Device;
import com.driverstack.yunos.domain.DeviceConfigurationItem;
import com.driverstack.yunos.remote.vo.DeviceClass;

public interface DeviceClassService {

	List<com.driverstack.yunos.domain.DeviceClass> getAll(String locale);

}
