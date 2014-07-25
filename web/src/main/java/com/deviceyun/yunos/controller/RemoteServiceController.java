package com.deviceyun.yunos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deviceyun.yunos.service.RemoteServiceImpl;

/**
 * Restful API for remote service
 * 
 * @author jackding
 * 
 */
@RestController
@RequestMapping("service")
public class RemoteServiceController {

	@Autowired
	private RemoteServiceImpl remoteService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam("userId") String userId,
			@RequestParam("password") String password) {
		return remoteService.login(userId, password);
	}

	@RequestMapping(value = "/listUserDevice", method = RequestMethod.GET)
	public List<com.deviceyun.yunos.remote.vo.Device> listUserDevice(
			@RequestParam("userId") String userId) {

		return remoteService.listDevice(userId);
	}

}
