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
import com.deviceyun.yunos.domain.Token;
import com.deviceyun.yunos.remote.vo.Device;

/**
 * remote service for client, such as mobile app
 * 
 * @author jackding
 * 
 */
@Component
public class RemoteServiceImpl implements RemoteService {

	@Autowired
	private DeviceManager deviceManager;
	@Autowired
	private DeviceService deviceService;
	@Autowired
	private AuthorizationService authorizationService;

	@Autowired
	private ApplicationDao applicationDao;

	public DeviceManager getDeviceManager() {
		return deviceManager;
	}

	public void setDeviceManager(DeviceManager deviceManager) {
		this.deviceManager = deviceManager;
	}

	@Override
	public String login(String userId, String password) {
		Token t = authorizationService.login(userId, password);

		if (t != null)
			return t.getId();
		else
			return null;
	}

	@Override
	public List<Device> listDevice(String userId) {
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

	@Override
	public void addDevice(String userId, Device device) {
		com.deviceyun.yunos.domain.Device domainDevice = new com.deviceyun.yunos.domain.Device();
		BeanUtils.copyProperties(device, domainDevice);

		deviceService.saveDevice(domainDevice);
	}

	@Override
	public void removeDevice(String deviceId) {
		deviceService.remove(deviceId);
	}

	@Override
	public Object loadDeviceConfig(String deviceId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateDeviceConfig(String deviceId, String config) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object operateDevice(String deviceId, int functionalDeviceIndex,
			String operation, Map<String, String> parameters) {

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
}
