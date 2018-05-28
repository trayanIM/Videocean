package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.model.User;
import com.example.model.UserDAO;
import com.example.model.UserProblemException;

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
		} catch (UserProblemException e) {
			e.printStackTrace();
			viewModel.addAttribute("error",
					"There is already a user with this email or you haven't entered a fullname");
			return "signUp";
		}
		return "redirect:index";
	}
}
