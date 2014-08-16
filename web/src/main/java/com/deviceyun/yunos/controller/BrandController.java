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
import com.deviceyun.yunos.remote.vo.Brand;
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
@RequestMapping("/1.0/brands")
// @Secured("ROLE_USER")
public class BrandController {

	@Autowired
	private RemoteService remoteService;
	 
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<Brand> getAllBrands(@RequestParam("language")String language) {
		return remoteService.getAllBrands(language);
	}
}
