package com.driverstack.yunos.service;

import java.util.List;
import java.util.Locale;

import com.driverstack.yunos.domain.Vendor;

public interface VendorService {

	Vendor loadByCodeName(String codeName);
	
	
}
