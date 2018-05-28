package com.videocean.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.videocean.model.User;
import com.videocean.service.dao.UserDAO;
import com.videocean.exception.UserException;

@Controller
public class SignInController {
	private static final int MAX_INACTIVE_DAYS = 300000;

	@RequestMapping(method = RequestMethod.GET, value = "/signIn")
	public String showClientDetails(Model viewModel) {
		viewModel.addAttribute("user", new User());
		return "signIn";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/signIn")
	public String showNewClientForm(@ModelAttribute User user, Model viewModel, HttpServletRequest request) {
		UserDAO userDao = new UserDAO();
		try {
			User us = userDao.getUserByEmailAndPass(user.getUsername(), user.getPassword());
			System.out.println();
			HttpSession session = request.getSession();
			session.setAttribute("user", us);
			session.setMaxInactiveInterval(MAX_INACTIVE_DAYS);
			// viewModel.addAttribute("user",us);
		} catch (UserException e) {
			e.printStackTrace();
			viewModel.addAttribute("error", "Wrong email or password");
			return "signIn";
		}
		return "redirect:index";
	}
}
