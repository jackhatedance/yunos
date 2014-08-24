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
@RequestMapping("/1.0/drivers")
// @Secured("ROLE_USER")
public class DriverController {

	@Autowired
	private RemoteService remoteService;
	
	

	@RequestMapping(value = "/{driverId}/configureItems", method = RequestMethod.GET)
	public com.deviceyun.yunos.remote.vo.Device getDevice(
			@PathVariable("driverId") String driverId) {

		return remoteService.getDevice(driverId);
	}

	

	
}
