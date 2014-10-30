package com.driverstack.yunos.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.driverstack.yunos.remote.vo.User;
import com.driverstack.yunos.service.RemoteService;

/**
 * 
 * 
 * @author jackding
 * 
 */
@RestController
@RequestMapping("/api/1.0/users")
@Secured("ROLE_APPLICATION")
public class UserApiController {

	@Autowired
	private RemoteService remoteService;

	/**
	 * a user need username/password to login, get a access token. then use the
	 * token until logout.
	 * 
	 * @param vendorId
	 * @param locale
	 * @return
	 */
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public User getUser(@PathVariable String userId) {

		User user = remoteService.getUser(userId);

		return user;
	}

}
