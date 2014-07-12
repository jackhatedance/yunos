package com.deviceyun.yunos.api.driver;

import java.util.Date;
import java.util.List;

import com.deviceyun.yunos.api.device.DeviceApi;
import com.deviceyun.yunos.api.device.DeviceInfo;
import com.deviceyun.yunos.api.device.FunctionalDevice;
import com.deviceyun.yunos.api.device.Model;

/**
 * a driver submit by developers.
 * 
 * @author jackding
 * 
 */
public interface Driver {

	String getAuthor();

	Date getReleaseDate();

	String getSdkVersion();

	List<Model> getSupportedModels();

	List<ConfigItem> getConfigItems();

	DeviceApi getFunctionalDeviceApi();

	String getFunctionalDeviceApiVersion();

	FunctionalDevice createDevice(DeviceInfo info);
}
