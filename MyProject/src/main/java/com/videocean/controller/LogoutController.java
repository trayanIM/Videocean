package com.videocean.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LogoutController {

	@RequestMapping(method = RequestMethod.GET, value = "/logout")
	public String showClientDetails(HttpServletRequest request, HttpServletResponse response) {
		// DA SE OPRAVI!!!!
		if (request.getSession().getAttribute("user") != null) {
			request.getSession().invalidate();
		}

		return "redirect:index";
	}

}
