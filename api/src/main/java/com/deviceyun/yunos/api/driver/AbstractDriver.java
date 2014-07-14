package com.deviceyun.yunos.api.driver;

import java.util.ArrayList;
import java.util.List;

import com.deviceyun.yunos.api.device.Model;

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

}
