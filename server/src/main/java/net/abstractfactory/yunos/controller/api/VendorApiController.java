package net.abstractfactory.yunos.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.abstractfactory.yunos.domain.Vendor;
import net.abstractfactory.yunos.remote.vo.Model;
import net.abstractfactory.yunos.service.RemoteService;

/**
 * Restful API for remote service
 * 
 * @author jackding
 * 
 */
@RestController
@RequestMapping("/api/1.0/vendors")
// @Secured("ROLE_USER")
public class VendorApiController {

	@Autowired
	private RemoteService remoteService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<net.abstractfactory.yunos.remote.vo.Vendor> getAll(
			@RequestParam("locale") String locale) {
		return remoteService.getAllVendors(locale);
	}

	@RequestMapping(value = "/{vendorId}/models", method = RequestMethod.GET)
	public List<Model> getModels(
			@PathVariable("vendorId") String vendorId,
			@RequestParam(value = "deviceClassId", required = false) String deviceClassId,
			@RequestParam("locale") String locale) {

		return remoteService.getModels(vendorId, deviceClassId, locale);
	}
}
