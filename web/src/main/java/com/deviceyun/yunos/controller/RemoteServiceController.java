package com.deviceyun.yunos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
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
@RequestMapping("/1.0/service")
public class RemoteServiceController {

	@Autowired
	private RemoteService remoteService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@Secured("ROLE_USER")
	public String login(@RequestParam("userId") String userId,
			@RequestParam("password") String password) {
		return remoteService.login(userId, password);
	}

	@RequestMapping(value = "/listUserDevice")
	public List<com.deviceyun.yunos.remote.vo.Device> listUserDevice(
			@RequestParam("userId") String userId) {

		return remoteService.listDevice(userId);
	}

	@RequestMapping(value = "/addDevice", method = RequestMethod.POST)
	public void addDevice(String userId, Device device) {
		remoteService.addDevice(userId, device);
	}

	@RequestMapping(value = "/removeDevice", method = RequestMethod.POST)
	public void removeDevice(String deviceId) {
		remoteService.removeDevice(deviceId);
	}

}
