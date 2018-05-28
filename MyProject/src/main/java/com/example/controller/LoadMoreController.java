package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.model.Clip;
import com.example.model.ClipDAO;
import com.example.model.ClipException;

@Controller
public class LoadMoreController {

	@RequestMapping(method = RequestMethod.GET, value = "/loadmore")
	public String showClientDetails(Model viewModel, HttpServletRequest request) {
		int max = (int) request.getSession().getAttribute("count");
		List<Clip> forOtherPage = new ArrayList<Clip>();
		try {
			List<Clip> clips = new ClipDAO().getAllClips();
			if ((max - 1) < (clips.size() - 1)) {
				for (int first = max; first < max + 8 && first < clips.size(); first++) {

					forOtherPage.add(clips.get(first));

				}
				request.getSession().setAttribute("count", max + 8);
				viewModel.addAttribute("clips", forOtherPage);
				return "index";
			} else {
				return "redirect:index";
			}

		} catch (ClipException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "redirect:index";
		}

	}

}
