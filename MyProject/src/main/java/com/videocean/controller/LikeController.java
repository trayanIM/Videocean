package com.videocean.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LikeController {

    @RequestMapping(method = RequestMethod.POST, value = "like-{id}")
    public String updateLikes(@PathVariable("id") Integer id, Model viewModel, HttpServletRequest request) {
        return null;
    }

}
