package com.driverstack.yunos.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.driverstack.yunos.remote.vo.DeviceClass;
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
@RequestMapping("/api/1.0/deviceClasses")
@Secured({ "ROLE_USER", "ROLE_APPLICATION" })
public class DeviceClassApiController {

	@Autowired
	private RemoteService remoteService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<DeviceClass> getModels(
			@RequestParam(value = "vendorId", required = false) String vendorId,
			@RequestParam("locale") String locale) {

		return remoteService.getDeviceClasses(vendorId, locale);

	}

}
