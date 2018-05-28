package com.videocean.controller;

import com.videocean.exception.UserException;
import com.videocean.model.User;
import com.videocean.service.dao.UserDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.logging.Logger;

@Controller
public class SignUpController {

    private Logger logger = Logger.getLogger(SignUpController.class.getName());

    @RequestMapping(method = RequestMethod.GET, value = "/signUp")
    public String getSignUpPage(Model viewModel) {
        viewModel.addAttribute("user", new User());
        return "signUp";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/signUp")
    public String signUp(@ModelAttribute User user, Model viewModel) {
        UserDAO userDao = new UserDAO();
        try {
            userDao.addUser(user);
        } catch (UserException e) {
            String errorMessage = "User with the provided username already exists!";
            logger.info(errorMessage);
            viewModel.addAttribute("error", errorMessage);
            return "signUp";
        }
        return "redirect:index";
    }
}
