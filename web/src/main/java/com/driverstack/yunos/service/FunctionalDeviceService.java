package com.driverstack.yunos.service;

import java.io.InputStream;
import java.io.Serializable;

import com.driverstack.yunos.domain.FunctionalDevice;

public interface FunctionalDeviceService {
	
	
	void delete(Serializable id);
	FunctionalDevice getByClassName(String className);
	Serializable upload(InputStream in);
	
	Serializable save(FunctionalDevice obj);
	
}
