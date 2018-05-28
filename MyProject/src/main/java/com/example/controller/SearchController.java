package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.Clip;
import com.example.model.ClipDAO;
import com.example.model.ClipException;
import com.example.model.User;

@Controller
public class SearchController {
	@RequestMapping(method = RequestMethod.GET, value = "/search")
	public String ShowAllClipsFromSearch(Model viewModel, HttpServletRequest request,
			@RequestParam("search") String search) {

		List<Clip> clips = new ArrayList<Clip>();
		ClipDAO ClipDAO = new ClipDAO();
		try {
			System.out.println(search);
			clips = ClipDAO.getClipsByStrinInName(search);
			viewModel.addAttribute("clips", clips);
			viewModel.addAttribute("search", search);
		} catch (ClipException e) {
			viewModel.addAttribute("error", "Problems whith clip serach by string");
			e.printStackTrace();
			return "redirect:index";
		}
		if (clips.isEmpty() == true) {
			viewModel.addAttribute("errorMessage", "Sorry, there are no such clips ");
		}
		User user = (User) request.getSession().getAttribute("user");
		viewModel.addAttribute("user", user);

		return "searchResult";
	}
}
