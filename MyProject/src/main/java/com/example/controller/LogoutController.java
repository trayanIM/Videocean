package com.example.controller;

import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.net.jsse.openssl.Authentication;
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
