package com.videocean.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/privacy")
public class PrivacyController {

	@RequestMapping(method = RequestMethod.GET)
	public String getPrivacyPage(Model model) {
		return "privacy";
	}

}