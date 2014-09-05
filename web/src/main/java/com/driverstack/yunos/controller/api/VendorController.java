package com.driverstack.yunos.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.driverstack.yunos.domain.Vendor;
import com.driverstack.yunos.remote.vo.Model;
import com.driverstack.yunos.service.RemoteService;

/**
 * Restful API for remote service
 * 
 * @author jackding
 * 
 */
@RestController
@RequestMapping("/api/1.0/vendors")
// @Secured("ROLE_USER")
public class VendorController {

	@Autowired
	private RemoteService remoteService;
	 
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<com.driverstack.yunos.remote.vo.Vendor> getAll(@RequestParam("locale")String locale) {
		return remoteService.getAllVendors(locale);
	}
	
	@RequestMapping(value = "/{id}/models", method = RequestMethod.GET)
	public List<Model> getModels(@PathVariable("id") String id, @RequestParam("locale")String locale) {
		return remoteService.getModels(id,locale);
	}
	
}
