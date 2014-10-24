package com.driverstack.yunos.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class BaseController {

	void setHeaderModel(Model model) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String username = "n/a";
		if (auth != null) {
			Object principal = auth.getPrincipal();
			if (principal instanceof String)
				username = (String) principal;
			else if (principal instanceof UserDetails) {
				UserDetails details = (UserDetails) auth.getPrincipal();
				username = details.getUsername();
			} else
				username = principal.toString();
		}
		model.addAttribute("username", username);
	}

}
