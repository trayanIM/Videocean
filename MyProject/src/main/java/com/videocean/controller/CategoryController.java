package com.videocean.controller;

import com.videocean.exception.CategoryException;
import com.videocean.exception.ClipException;
import com.videocean.model.Category;
import com.videocean.model.Clip;
import com.videocean.service.dao.CategoryDAO;
import com.videocean.service.dao.ClipDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.logging.Logger;

@Controller
public class CategoryController {

    private Logger logger = Logger.getLogger(CategoryController.class.getName());

    @RequestMapping(method = RequestMethod.GET, value = "/categories-{id}")
    public String getCategoryById(@PathVariable("id") Integer id, Model viewModel) {
        List<Clip> clips;
        try {
            Category currentCategory = new CategoryDAO().getCategoryByID(id);
            clips = new ClipDAO().getAllClipsByCategory(currentCategory);
            viewModel.addAttribute("category", currentCategory);
            viewModel.addAttribute("clips", clips);

        } catch (ClipException | CategoryException e) {
            logger.info("An error occurred while getting videos by category!");
            return "redirect:index:";
        }

        if (clips.isEmpty()) {
            viewModel.addAttribute("errorMessage", "Clips from the specified category are not found! ");
        }

        return "movies";
    }

}