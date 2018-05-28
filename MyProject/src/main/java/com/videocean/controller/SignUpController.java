package com.videocean.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.videocean.model.User;
import com.videocean.service.dao.UserDAO;
import com.videocean.exception.UserException;

@Controller
// @SessionAttributes("user")
public class SignUpController {

	@RequestMapping(method = RequestMethod.GET, value = "/signUp")
	public String showClientDetails(Model viewModel) {
		viewModel.addAttribute("user", new User());
		return "signUp";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/signUp")
	public String showNewClientForm(@ModelAttribute User user, Model viewModel) {
		UserDAO userDao = new UserDAO();
		try {
			userDao.addUser(user);
		} catch (UserException e) {
			e.printStackTrace();
			viewModel.addAttribute("error",
					"There is already a user with this email or you haven't entered a fullname");
			return "signUp";
		}
		return "redirect:index";
	}
}
