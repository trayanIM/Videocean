package com.example.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.model.User;

@Controller
public class LikeController {

	@RequestMapping(method = RequestMethod.POST, value = "like-{id}")
	public String showNewClientForm(@PathVariable("id") Integer id, Model viewModel, HttpServletRequest request) {

		return null;

	}

}
