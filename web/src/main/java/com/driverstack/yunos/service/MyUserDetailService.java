package com.driverstack.yunos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.driverstack.yunos.domain.User;
import com.driverstack.yunos.web.security.MyUserDetail;

@Component
public class MyUserDetailService implements UserDetailsService {
	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {

		User user = userService.getUser(username);

		MyUserDetail detail = new MyUserDetail(user);
		
		detail.getUsername();

		return detail;
	}

}
