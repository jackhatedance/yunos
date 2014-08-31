package com.driverstack.yunos.controller.api;

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

import com.driverstack.yunos.dao.ApplicationDao;
import com.driverstack.yunos.domain.Application;
import com.driverstack.yunos.remote.vo.Brand;
import com.driverstack.yunos.remote.vo.Device;
import com.driverstack.yunos.remote.vo.Product;
import com.driverstack.yunos.service.ApplicationService;
import com.driverstack.yunos.service.RemoteService;

/**
 * Restful API for remote service
 * 
 * @author jackding
 * 
 */
@RestController
@RequestMapping("/api/1.0/brands")
// @Secured("ROLE_USER")
public class BrandController {

	@Autowired
	private RemoteService remoteService;
	 
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<Brand> getAllBrands(@RequestParam("locale")String locale) {
		return remoteService.getAllBrands(locale);
	}
	
	@RequestMapping(value = "/{id}/products", method = RequestMethod.GET)
	public List<Product> getProducts(@PathVariable("id") String id, @RequestParam("locale")String locale) {
		return remoteService.getProducts(id,locale);
	}
	
}
