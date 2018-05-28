package com.videocean.controller;

import com.videocean.exception.ClipException;
import com.videocean.model.Clip;
import com.videocean.model.User;
import com.videocean.service.dao.ClipDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchController {
    @RequestMapping(method = RequestMethod.GET, value = "/search")
    public String searchClipByString(Model viewModel, HttpServletRequest request,
                                     @RequestParam("search") String search) {

        List<Clip> clips = new ArrayList<Clip>();
        ClipDAO ClipDAO = new ClipDAO();
        try {
            clips = ClipDAO.getClipsByStringInName(search);
            viewModel.addAttribute("clips", clips);
            viewModel.addAttribute("search", search);
        } catch (ClipException e) {
            viewModel.addAttribute("error", "Problem while loading clips!");
            e.printStackTrace();
            return "redirect:index";
        }
        if (clips.isEmpty()) {
            String errorMessage = String.format("There are not clips found containing %s word!", search);
            viewModel.addAttribute("errorMessage", errorMessage);
        }
        User user = (User) request.getSession().getAttribute("user");
        viewModel.addAttribute("user", user);

        return "searchResult";
    }
}
