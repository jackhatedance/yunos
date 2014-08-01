package com.deviceyun.yunos.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.deviceyun.yunos.dao.ApplicationDao;
import com.deviceyun.yunos.domain.Application;
import com.deviceyun.yunos.remote.vo.Device;
import com.deviceyun.yunos.service.ApplicationService;
import com.deviceyun.yunos.service.RemoteService;

/**
 * Restful API for remote service
 * 
 * @author jackding
 * 
 */
@RestController
@RequestMapping("/1.0/devices")
// @Secured("ROLE_USER")
public class DeviceController {

	@Autowired
	private RemoteService remoteService;
	@Autowired
	ApplicationService applicationService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<com.deviceyun.yunos.remote.vo.Device> listUserDevices(
			@RequestParam("userId") String userId) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String name = auth.getName(); // get logged in username
		// User user =
		// (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		// String name = user.getUsername(); //get logged in username
		return remoteService.listDevice(userId);
	}

	@RequestMapping(value = "/{deviceId}", method = RequestMethod.GET)
	public com.deviceyun.yunos.remote.vo.Device getDevice(
			@PathVariable("deviceId") String deviceId) {

		return remoteService.getDevice(deviceId);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public void addDevice(String userId, Device device) {
		remoteService.addDevice(userId, device);
	}

	@RequestMapping(value = "/{deviceId}", method = RequestMethod.DELETE)
	public void removeDevice(@PathVariable String deviceId) {
		remoteService.removeDevice(deviceId);
	}

	@RequestMapping(value = { "/{deviceId}/{functionalDeviceIndex}/{operation}" }, method = {
			RequestMethod.POST, RequestMethod.GET })
	public void operate(@PathVariable String deviceId,
			@PathVariable int functionalDeviceIndex,
			@PathVariable String operation,
			@RequestParam Map<String, String> parameters) {

		
		String appId = parameters.get("appId");

		
		if (!applicationService.isValid(appId))
			throw new RuntimeException("error:appId invalid");

		// String appKey = parameters.get(APP_KEY);

		Object result = remoteService.operateDevice(deviceId,
				functionalDeviceIndex, operation, parameters);

	}
}
