package net.abstractfactory.yunos.service;

import java.io.InputStream;
import java.io.Serializable;

import net.abstractfactory.yunos.domain.FunctionalDevice;

public interface FunctionalDeviceService {
	
	
	void delete(Serializable id);
	FunctionalDevice get(String organizationId, String artifactId);
	FunctionalDevice getByClassName(String className);
	Serializable upload(InputStream in);
	
	Serializable save(FunctionalDevice obj);
	
}
