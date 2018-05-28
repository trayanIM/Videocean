package com.videocean.controller;

import com.videocean.exception.UserException;
import com.videocean.model.Playlist;
import com.videocean.model.User;
import com.videocean.service.dao.UserDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping(value = "/library")
public class LibraryController {

    private Logger logger = Logger.getLogger(LibraryController.class.getName());

    @RequestMapping(method = RequestMethod.GET)
    public String getLibraryPage(Model model, Model viewModel, HttpServletRequest request, HttpServletResponse response) {

        if (request.getSession().getAttribute("user") == null) {
            return "error";
        }

        User user = (User) request.getSession().getAttribute("user");
        try {
            List<Playlist> library = new UserDAO().getAllPlaylistsForUser(user.getUserID());
            viewModel.addAttribute(library);

        } catch (UserException e) {
            logger.info(e.getMessage());
        }
        viewModel.addAttribute("user", user);

        return "library";
    }

}
