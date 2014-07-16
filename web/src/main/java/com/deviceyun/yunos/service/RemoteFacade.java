package com.deviceyun.yunos.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.deviceyun.yunos.api.ApiUtils;
import com.deviceyun.yunos.api.Parameter;
import com.deviceyun.yunos.api.ParameterType;
import com.deviceyun.yunos.api.device.FunctionalDevice;
import com.deviceyun.yunos.core.DeviceManagerImpl;

@Component
public class RemoteFacade {
	public static final String API_KEY = "apiKey";
	public static final String DEVICE_ID = "deviceId";
	public static final String OPERATION = "operation";

	@Autowired
	private DeviceManagerImpl deviceManager;

	public DeviceManagerImpl getDeviceManager() {
		return deviceManager;
	}

	public void setDeviceManager(DeviceManagerImpl deviceManager) {
		this.deviceManager = deviceManager;
	}

	public Object urlApi(Map<String, String> parameters) {
		// String apiKey = parameters.get(API_KEY);

		String deviceId = parameters.get(DEVICE_ID);
		String operation = parameters.get(OPERATION);

		FunctionalDevice device = deviceManager
				.getFunctionDeviceObject(deviceId);
		// perform operation on device
		// Object ret = device.invoke(operation, null);
		Method method = ApiUtils.getMethod(device, operation);

		List<Parameter> paramList = ApiUtils.getParameterInfo(method);

		int paramSize = paramList.size();
		Object[] parameterObjects = new Object[paramSize];

		for (int i = 0; i < paramSize; i++) {
			Parameter p = paramList.get(i);
			String value = parameters.get(p.getName());

			ParameterType pt = ParameterType.getType(p.getType());
			Object objValue = pt.fromString(value);

			parameterObjects[i] = objValue;
		}

		try {
			return method.invoke(device, parameterObjects);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

}
