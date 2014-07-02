package com.deviceyun.smarthome.api.driver;

import java.util.Date;
import java.util.List;

import com.deviceyun.smarthome.api.device.DeviceInfo;
import com.deviceyun.smarthome.api.device.FunctionDevice;
import com.deviceyun.smarthome.api.device.Model;

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

	FunctionDevice createDevice(DeviceInfo info);
}
