package com.deviceyun.yunos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deviceyun.yunos.remote.vo.Device;
import com.deviceyun.yunos.service.RemoteService;

/**
 * Restful API for remote service
 * 
 * @author jackding
 * 
 */
@RestController
@RequestMapping("/1.0/devices")
@Secured("ROLE_ADMIN")
public class DeviceController {

	@Autowired
	private RemoteService remoteService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<com.deviceyun.yunos.remote.vo.Device> listUserDevices(
			@RequestParam("userId") String userId) {

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

}
