package com.example.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.model.Clip;
import com.example.model.ClipDAO;
import com.example.model.ClipException;
import com.google.gson.Gson;

@org.springframework.web.bind.annotation.RestController
public class RestController {

	@RequestMapping(method = RequestMethod.GET, value = "/developers-{id}")
	public void greeting(@PathVariable("id") Integer id, Model viewModel, HttpServletResponse response) {

		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		Clip jsonForThisClip;
		try {
			jsonForThisClip = new ClipDAO().getClipByID(id);
			response.getWriter().println(new Gson().toJson(jsonForThisClip));
		} catch (ClipException | IOException e) {
			e.printStackTrace();
		}
	}
}
