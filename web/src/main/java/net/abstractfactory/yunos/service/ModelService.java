package net.abstractfactory.yunos.service;

import java.util.List;

import net.abstractfactory.yunos.domain.DeviceClass;
import net.abstractfactory.yunos.domain.Model;
import net.abstractfactory.yunos.domain.Vendor;

public interface ModelService {

	List<Model> getModels(Vendor vendor, DeviceClass deviceClass, String locale);
}
