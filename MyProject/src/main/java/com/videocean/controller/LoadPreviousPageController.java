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
import com.videocean.service.dao.ClipDAO;
import com.videocean.exception.ClipException;

@Controller
public class LoadPreviousPageController {

	private Logger logger = Logger.getLogger(LoadPreviousPageController.class.getName());

	@RequestMapping(method = RequestMethod.GET, value = "/loadprevious")
	public String getPreviousPage(Model viewModel, HttpServletRequest request) {
		int min = (int) request.getSession().getAttribute("count");
		List<Clip> forOtherPage = new ArrayList<Clip>();
		try {
			List<Clip> clips = new ClipDAO().getAllClips();
			if (min - 8 > 0) {
				for (int first = min - 16; first < min - 8 && first < clips.size(); first++) {

					forOtherPage.add(clips.get(first));

				}
				request.getSession().setAttribute("count", min - 8);
				viewModel.addAttribute("clips", forOtherPage);
				return "index";
			} else {
				return "redirect:index";
			}

		} catch (ClipException e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
			return "redirect:index";
		}

	}

}
