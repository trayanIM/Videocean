package com.videocean.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.videocean.model.Clip;
import com.videocean.model.User;
import com.videocean.service.dao.UserDAO;
import com.videocean.exception.UserException;

@Controller
public class HistoryController {

	private Logger logger = Logger.getLogger(HistoryController.class.getName());

	@RequestMapping(method = RequestMethod.GET, value = "/history")
	public String getUserHistory(Model viewModel, HttpServletRequest request) {

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
		} catch (UserException e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
		}
		return "history";
	}
}
