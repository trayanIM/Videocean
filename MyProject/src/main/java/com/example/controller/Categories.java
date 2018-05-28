package com.example.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.model.Category;
import com.example.model.CategoryDAO;
import com.example.model.CategoryException;
import com.example.model.Clip;
import com.example.model.ClipDAO;
import com.example.model.ClipException;

@Controller
public class Categories {

	@RequestMapping(method = RequestMethod.GET, value = "/categories-{id}")
	public String sayHello(@PathVariable("id") Integer id, Model viewModel) {
		List<Clip> clips;
		try {
			Category thisCategory = new CategoryDAO().getCategoryByID(id);
			clips = new ClipDAO().getAllClipsByCategory(thisCategory);
			viewModel.addAttribute("category", thisCategory);
			viewModel.addAttribute("clips", clips);

		} catch (ClipException | CategoryException e) {
			e.printStackTrace();
			return "redirect:index:";
		}

		if (clips.isEmpty() == true) {
			viewModel.addAttribute("errorMessage", "Sorry, there are no such clips ");
		}

		return "movies";
	}

}