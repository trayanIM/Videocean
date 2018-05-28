package com.videocean.controller;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.videocean.model.Clip;
import com.videocean.service.dao.ClipDAO;
import com.videocean.exception.ClipException;
import com.google.gson.Gson;

@org.springframework.web.bind.annotation.RestController
public class RestController {

	private Logger logger = Logger.getLogger(RestController.class.getName());

	@RequestMapping(method = RequestMethod.GET, value = "/developers-{id}")
	public void getClipInformation(@PathVariable("id") Integer id, Model viewModel, HttpServletResponse response) {

		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		Clip jsonForThisClip;
		try {
			jsonForThisClip = new ClipDAO().getClipByID(id);
			response.getWriter().println(new Gson().toJson(jsonForThisClip));
		} catch (ClipException | IOException e) {
			logger.info(e.getMessage());
		}
	}
}
