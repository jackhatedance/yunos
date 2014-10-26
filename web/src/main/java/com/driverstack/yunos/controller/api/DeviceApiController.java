package com.driverstack.yunos.controller.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.driverstack.yunos.remote.vo.ConfigurationItem;
import com.driverstack.yunos.remote.vo.Device;
import com.driverstack.yunos.service.ApplicationService;
import com.driverstack.yunos.service.RemoteService;

/**
 * Restful API for remote service
 * 
 * @author jackding
 * 
 */
@RestController
@RequestMapping("/api/1.0/devices")
@Secured({ "ROLE_USER", "ROLE_APPLICATION" })
public class DeviceApiController {

	@Autowired
	private RemoteService remoteService;
	@Autowired
	ApplicationService applicationService;

	@Secured("ROLE_USER")
	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<com.driverstack.yunos.remote.vo.Device> listUserDevices(
			@RequestParam("userId") String userId,
			@RequestParam(value = "deviceClassId", required = false) String deviceClassId) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String name = auth.getName(); // get logged in username
		// User user =
		// (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		// String name = user.getUsername(); //get logged in username
		return remoteService.queryUserDevices(userId, deviceClassId);
	}

	@RequestMapping(value = "/{deviceId}", method = RequestMethod.GET)
	public com.driverstack.yunos.remote.vo.Device getDevice(
			@PathVariable("deviceId") String deviceId,
			@RequestParam("locale") String locale) {

		return remoteService.getDevice(deviceId,locale);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public void addDevice(@RequestParam String userId,			
			@RequestBody Device device) {
		remoteService.addDevice(userId, device);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public boolean updateDevice(@RequestBody Device device) {
		remoteService.updateDevice(device);
		return true;// always true, ignore it on client side
	}

	@RequestMapping(value = "/{deviceId}", method = RequestMethod.DELETE)
	public boolean removeDevice(@PathVariable String deviceId) {
		remoteService.removeDevice(deviceId);
		return true;
	}

	@RequestMapping(value = { "/{deviceId}/{functionalDeviceIndex}/{operation}" }, method = {
			RequestMethod.POST, RequestMethod.GET })
	public boolean operate(@PathVariable String deviceId,
			@PathVariable int functionalDeviceIndex,
			@PathVariable String operation,
			@RequestParam Map<String, String> parameters) {

		String appId = parameters.get("appId");

		if (!applicationService.isValid(appId))
			throw new RuntimeException("error:appId invalid");

		// String appKey = parameters.get(APP_KEY);

		Object result = remoteService.operateDevice(deviceId,
				functionalDeviceIndex, operation, parameters);

		return true;

	}

	/**
	 * just generate initial configuration items, won't save to DB with these
	 * values and driver ID.
	 * 
	 * @param driverId
	 */
	@RequestMapping(value = "/{deviceId}/initialConfiguration", method = RequestMethod.GET)
	public List<ConfigurationItem> createConfiguration(
			@PathVariable String deviceId, @RequestParam String driverId) {

		return remoteService.getDeviceInitialConfiguration(deviceId, driverId);

	}

	/**
	 * get current configurations
	 * 
	 * @param deviceId
	 * @return
	 */
	@RequestMapping(value = "/{deviceId}/configuration", method = RequestMethod.GET)
	public List<ConfigurationItem> getConfiguration(
			@PathVariable String deviceId) {
		return remoteService.getDeviceConfiguration(deviceId);

	}

	@RequestMapping(value = "/{deviceId}/configuration", method = RequestMethod.POST)
	public boolean updateConfiguration(@PathVariable String deviceId,
			@RequestBody List<ConfigurationItem> configurationItems) {
		remoteService.updateDeviceConfiguration(deviceId, configurationItems);
		// always true.
		return true;

	}

	@RequestMapping(value = "/reload", method = RequestMethod.GET)
	public boolean reloadDevice(@RequestParam String deviceId) {
		remoteService.reloadDriver(deviceId);
		// always true.
		return true;

	}

}
