package com.videocean.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/creators")
public class CreatorController {

	@RequestMapping(method = RequestMethod.GET)
	public String getCreaterInfo(Model model) {
		return "creators";
	}

}
