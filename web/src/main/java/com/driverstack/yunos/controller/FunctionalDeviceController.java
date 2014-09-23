package com.driverstack.yunos.controller;

import java.io.FileInputStream;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.driverstack.yunos.service.DriverService;
import com.driverstack.yunos.service.FunctionalDeviceService;

@Controller
@RequestMapping(value = "/functional-device")
public class FunctionalDeviceController {
	@Autowired
	private FunctionalDeviceService functionalDeviceService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String displayForm() {
		return "functional-device/main";
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String save(@RequestParam("file") MultipartFile file, Model model) {
		String name = "";
		String msg = "";
		if (!file.isEmpty()) {
			try {
				name = file.getName();
				functionalDeviceService.upload(file.getInputStream());

				msg = "You successfully uploaded file=" + name;
			} catch (Exception e) {
				msg = "You failed to upload " + name + " => " + e.getMessage();
			}
		} else {
			msg = "You failed to upload " + name
					+ " because the file was empty.";
		}

		model.addAttribute("operationResult", msg);

		return "functional-device/main";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(@RequestParam("id") String id, Model model) {

		String result = "ok";
		try {

			functionalDeviceService.delete(id);

		} catch (Exception e) {
			result = e.getLocalizedMessage();
		}

		model.addAttribute("operationResult", result);

		return "functional-device/main";

	}

}
