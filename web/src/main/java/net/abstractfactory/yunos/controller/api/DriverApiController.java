package net.abstractfactory.yunos.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.abstractfactory.yunos.service.RemoteService;

/**
 * Restful API for remote service
 * 
 * @author jackding
 * 
 */
@RestController
@RequestMapping("/api/1.0/drivers")
// @Secured("ROLE_USER")
public class DriverApiController {

	@Autowired
	private RemoteService remoteService;

	@RequestMapping(value = "/{driverId}/configrationItems", method = RequestMethod.GET)
	public List<net.abstractfactory.yunos.remote.vo.DriverConfigurationDefinitionItem> getDriverConfigurationDefinitionItems(
			@PathVariable("driverId") String driverId,
			@RequestParam("locale") String locale) {

		return remoteService.getDriverConfigurationDefinitionItems(driverId,
				locale);
	}

	/**
	 * list matching driver for specified model.
	 * 
	 * @param modelId
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<net.abstractfactory.yunos.remote.vo.Driver> findDrivers(
			@RequestParam("modelId") String modelId) {

		return remoteService.getAvailableDrivers(modelId);

	}

}
