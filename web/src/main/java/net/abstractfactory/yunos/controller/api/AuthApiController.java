package net.abstractfactory.yunos.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.abstractfactory.yunos.remote.vo.AccessToken;
import net.abstractfactory.yunos.service.RemoteService;
import net.abstractfactory.yunos.web.security.TokenUserDetails;

/**
 * 
 * 
 * @author jackding
 * 
 */
@RestController
@RequestMapping("/api/1.0/auth")
public class AuthApiController {

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
	@Secured("ROLE_USER")
	@RequestMapping(value = "/requestAccessToken")
	public AccessToken requestAccessToken() {

		UserDetails user = (UserDetails) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		return remoteService.requestAccessToken(user.getUsername());
	}
	
	@Secured("ROLE_USER")
	@RequestMapping(value = "changePassword")
	public boolean changePassword(@RequestParam String newPassword) {
		UserDetails user = (UserDetails) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		remoteService.changePassword(user.getUsername(), newPassword);

		return true;
	}
	@Secured("ROLE_APPLICATION")
	@RequestMapping(value = "destroyToken")
	public boolean destroyToken() {
		TokenUserDetails tud = (TokenUserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		remoteService.revokeAccessToken(tud.getUsername());

		return true;
	}



}
