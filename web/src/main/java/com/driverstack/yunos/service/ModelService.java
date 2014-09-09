package com.driverstack.yunos.service;

import java.util.List;

import com.driverstack.yunos.domain.DeviceClass;
import com.driverstack.yunos.domain.Model;
import com.driverstack.yunos.domain.Vendor;

public interface ModelService {

	List<Model> getModels(Vendor vendor, DeviceClass deviceClass, String locale);
}
