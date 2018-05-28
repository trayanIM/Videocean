package com.videocean.controller;

import com.videocean.exception.UserException;
import com.videocean.model.User;
import com.videocean.service.dao.UserDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.logging.Logger;

@Controller
public class SignInController {
    private static final int MAX_INACTIVE_DAYS = 300000;

    private Logger logger = Logger.getLogger(SignInController.class.getName());

    @RequestMapping(method = RequestMethod.GET, value = "/signIn")
    public String getSignInPage(Model viewModel) {
        viewModel.addAttribute("user", new User());
        return "signIn";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/signIn")
    public String signIn(@ModelAttribute User user, Model viewModel, HttpServletRequest request) {
        UserDAO userDao = new UserDAO();
        try {
            User us = userDao.getUserByEmailAndPass(user.getUsername(), user.getPassword());
            System.out.println();
            HttpSession session = request.getSession();
            session.setAttribute("user", us);
            session.setMaxInactiveInterval(MAX_INACTIVE_DAYS);
            // viewModel.addAttribute("user",us);
        } catch (UserException e) {
            String errorMessage = "Wrong email or password!";
            logger.info(errorMessage);
            viewModel.addAttribute("error", errorMessage);
            return "signIn";
        }
        return "redirect:index";
    }
}
