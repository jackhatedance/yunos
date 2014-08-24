package com.deviceyun.yunos.driver;

import java.util.ArrayList;
import java.util.List;

import com.deviceyun.yunos.device.DeviceInfo;
import com.deviceyun.yunos.device.Model;

public abstract class AbstractDriver implements Driver {

	protected String sdkVersion;

	protected List<Model> supportedModels = new ArrayList<Model>();

	

	public AbstractDriver() {
		sdkVersion = "1.0";
	}

	@Override
	public String getSdkVersion() {

		return sdkVersion;
	}

	@Override
	public List<Model> getSupportedModels() {

		return supportedModels;
	}

	@Override
	public Class getConfigureClass() {
		
		return null;
	}

	@Override
	public void install(DeviceInfo info) {

	}

	@Override
	public void uninstall(DeviceInfo info) {

	}

}
