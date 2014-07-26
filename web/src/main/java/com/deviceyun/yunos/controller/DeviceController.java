package com.deviceyun.yunos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.deviceyun.yunos.remote.vo.Device;
import com.deviceyun.yunos.service.RemoteServiceImpl;

/**
 * Restful API for remote service
 * 
 * @author jackding
 * 
 */
@RestController
@RequestMapping("/v1.0/{userId}/devices")
public class DeviceController {

	@Autowired
	private RemoteServiceImpl remoteService;

	@RequestMapping(value = "/by-user/{userId}", method = RequestMethod.GET)
	public List<com.deviceyun.yunos.remote.vo.Device> listUserDevices(
			@PathVariable("userId") String userId) {

		return remoteService.listDevice(userId);
	}

	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public void addDevice(String userId, Device device) {
		remoteService.addDevice(userId, device);
	}

	@RequestMapping(value = "/removeDevice", method = RequestMethod.GET)
	public void removeDevice(String deviceId) {
		remoteService.removeDevice(deviceId);
	}

}
