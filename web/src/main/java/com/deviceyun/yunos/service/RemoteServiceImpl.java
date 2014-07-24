package com.deviceyun.yunos.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.deviceyun.yunos.api.ApiUtils;
import com.deviceyun.yunos.api.Parameter;
import com.deviceyun.yunos.api.ParameterType;
import com.deviceyun.yunos.core.DeviceManager;
import com.deviceyun.yunos.dao.ApplicationDao;
import com.deviceyun.yunos.device.FunctionalDevice;
import com.deviceyun.yunos.device.PhysicalDevice;
import com.deviceyun.yunos.domain.Application;
import com.deviceyun.yunos.remote.vo.Device;
/**
 * remote service for client, such as mobile app
 * 
 * @author jackding
 *
 */
@Component
public class RemoteServiceImpl implements RemoteService {
	public static final String APP_ID = "appId";
	public static final String APP_KEY = "appKey";
	public static final String DEVICE_ID = "deviceId";
	public static final String FUNCTIONAL_DEVICE_INDEX = "fdi";
	public static final String OPERATION = "operation";

	@Autowired
	private DeviceManager deviceManager;
	@Autowired
	private DeviceService deviceService;

	@Autowired
	private ApplicationDao applicationDao;

	public DeviceManager getDeviceManager() {
		return deviceManager;
	}

	public void setDeviceManager(DeviceManager deviceManager) {
		this.deviceManager = deviceManager;
	}

	/* (non-Javadoc)
	 * @see com.deviceyun.yunos.service.RemoteService#urlApi(java.util.Map)
	 */
	@Override
	public Object urlApi(Map<String, String> parameters) {

		String appId = parameters.get(APP_ID);
		String appKey = parameters.get(APP_KEY);

		Application app = applicationDao.get(appId);
		if (app == null)
			return "error:appId invalid";

		String deviceId = parameters.get(DEVICE_ID);
		int functionalDeviceIndex = Integer.valueOf(parameters
				.get(FUNCTIONAL_DEVICE_INDEX));
		String operation = parameters.get(OPERATION);

		PhysicalDevice physicalDevice = deviceManager
				.getPhysicalDeviceObject(deviceId);

		FunctionalDevice functionalDevice = physicalDevice
				.getFunctionDevice(functionalDeviceIndex);
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

	public List<Device> getDevicesByUser(String userId) {
		List<com.deviceyun.yunos.domain.Device> domainDeviceList = deviceService
				.listByUserId(userId);
		List<Device> remoteDeviceList = new ArrayList<Device>();

		for (com.deviceyun.yunos.domain.Device d : domainDeviceList) {
			Device rd = new Device();

			BeanUtils.copyProperties(d, rd, "model");

			remoteDeviceList.add(rd);
		}
		return remoteDeviceList;
	}

}
