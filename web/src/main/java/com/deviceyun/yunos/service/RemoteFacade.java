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
import com.deviceyun.yunos.core.DeviceManager;
import com.deviceyun.yunos.device.FunctionalDevice;
import com.deviceyun.yunos.device.PhysicalDevice;

@Component
public class RemoteFacade {
	public static final String API_KEY = "apiKey";
	public static final String DEVICE_ID = "deviceId";
	public static final String FUNCTIONAL_DEVICE_INDEX = "functionalDeviceIndex";
	public static final String OPERATION = "operation";

	@Autowired
	private DeviceManager deviceManager;

	public DeviceManager getDeviceManager() {
		return deviceManager;
	}

	public void setDeviceManager(DeviceManager deviceManager) {
		this.deviceManager = deviceManager;
	}

	public Object urlApi(Map<String, String> parameters) {
		// String apiKey = parameters.get(API_KEY);

		String deviceId = parameters.get(DEVICE_ID);
		int functionalDeviceIndex = Integer.valueOf( parameters.get(FUNCTIONAL_DEVICE_INDEX));		
		String operation = parameters.get(OPERATION);

		PhysicalDevice physicalDevice = deviceManager
				.getPhysicalDeviceObject(deviceId);
		
		FunctionalDevice functionalDevice = physicalDevice.getFunctionDevice(functionalDeviceIndex);
		// perform operation on device
		// Object ret = device.invoke(operation, null);
		Method method = ApiUtils.getMethod(functionalDevice, operation);

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
			return method.invoke(functionalDevice, parameterObjects);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

}
