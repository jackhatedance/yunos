package com.driverstack.yunos.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.driverstack.yunos.remote.vo.DeviceClass;
import com.driverstack.yunos.remote.vo.FunctionalDevice;
import com.driverstack.yunos.remote.vo.HardwareType;
import com.driverstack.yunos.remote.vo.Model;
import com.driverstack.yunos.service.RemoteService;

/**
 * Restful API for remote service
 * 
 * @author jackding
 * 
 */
@RestController
@RequestMapping("/api/1.0/functionalDevices")
// @Secured("ROLE_USER")
public class FunctionalDeviceApiController {

	@Autowired
	private RemoteService remoteService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<DeviceClass> getModels(
			@RequestParam(value = "vendorId", required = false) String vendorId,
			@RequestParam("locale") String locale) {

		return remoteService.getDeviceClasses(vendorId, locale);

	}

	@RequestMapping(value = "/by-device", method = RequestMethod.GET)
	public List<FunctionalDevice> getFunctionalDevices(
			@RequestParam("deviceId") String deviceId,
			@RequestParam("locale") String locale) {
		return remoteService.getFunctionalDevices(deviceId, locale);
	}

	@RequestMapping(value = "/by-user", method = RequestMethod.GET)
	public List<FunctionalDevice> queryUserFunctionalDevices(
			@RequestParam("userId") String userId,			
			@RequestParam("className") String className,
			@RequestParam("locale") String locale) {
		return remoteService.queryUserFunctionalDevices(userId, 
				className, locale);

	}
}
