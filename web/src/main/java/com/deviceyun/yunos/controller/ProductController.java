package com.deviceyun.yunos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deviceyun.yunos.remote.vo.HardwareType;
import com.deviceyun.yunos.remote.vo.Model;
import com.deviceyun.yunos.service.RemoteService;

/**
 * Restful API for remote service
 * 
 * @author jackding
 * 
 */
@RestController
@RequestMapping("/1.0/products")
// @Secured("ROLE_USER")
public class ProductController {

	@Autowired
	private RemoteService remoteService;
	
	@RequestMapping(value = "/{id}/models", method = RequestMethod.GET)
	public List<Model> getModels(@PathVariable("id") String id, @RequestParam("locale")String locale) {
		return remoteService.getModels(id,locale);
	}
	
}
