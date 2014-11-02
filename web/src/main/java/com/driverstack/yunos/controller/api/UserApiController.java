package com.driverstack.yunos.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
	@Secured("ROLE_APPLICATION")
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public User getUser(@PathVariable String userId) {

		User user = remoteService.getUser(userId);

		return user;
	}

	 
	@RequestMapping(value = "", method = RequestMethod.POST)
	public boolean createUser(@RequestBody User user) {
		remoteService.createUser(user);
		return true;
	}

}
