package net.abstractfactory.yunos.service;

import java.util.List;

import net.abstractfactory.yunos.domain.Vendor;

public interface DeviceClassService {

	List<net.abstractfactory.yunos.domain.DeviceClass> find(Vendor vendor,
			String locale);
}
