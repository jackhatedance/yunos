package net.abstractfactory.yunos.service;

import java.util.List;
import java.util.Locale;

import net.abstractfactory.yunos.domain.Vendor;

public interface VendorService {

	Vendor loadByCodeName(String codeName);
	
	
}
