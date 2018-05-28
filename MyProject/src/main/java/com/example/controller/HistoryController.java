package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.model.Clip;
import com.example.model.User;
import com.example.model.UserDAO;
import com.example.model.UserProblemException;

@Controller
public class HistoryController {

	@RequestMapping(method = RequestMethod.GET, value = "/history")
	public String showClientDetails(Model viewModel, HttpServletRequest request) {

		if (request.getSession().getAttribute("user") == null) {
			return "error";
		}

		User user = (User) request.getSession().getAttribute("user");
		try {
			List<Clip> clipsForward = new UserDAO().getHistory(user.getUserID());
			List<Clip> clipsBack = new ArrayList<Clip>();
			for (int count = clipsForward.size() - 1; count >= 0; count--) {
				clipsBack.add(clipsForward.get(count));
			}
			viewModel.addAttribute("clips", clipsBack);
		} catch (UserProblemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Empty history");
		}
		return "history";
	}
}
