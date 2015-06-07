package com.driverstack.yunos.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.driverstack.yunos.core.ResoucePath;

@Controller
@RequestMapping(value = "/sysInfo")
public class SysInfoController extends BaseController {
	@Autowired
	ServletContext servletContext;
	
	@Autowired
	ResoucePath resourcePath;

	@RequestMapping("")
	public String index(Model model) {
		setHeaderModel(model);

		model.addAttribute("applicationVersion", getApplicationVersion());
		model.addAttribute("resourcePath", resourcePath.getResourcePath());
		
		return "sysinfo";
	}

	private String getApplicationVersion() {
		String name = "/META-INF/MANIFEST.MF";
		Properties props = new Properties();
		try {
			InputStream in = servletContext.getResourceAsStream(name);
			if (in == null)
				return "(dev-mode)";

			props.load(in);
			String buildNumber = (String) props.get("Implementation-Version");
			String version = (String) props.get("Specification-Version");
			return version + "-" + buildNumber;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}
}
