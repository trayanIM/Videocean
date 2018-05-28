package com.videocean.controller;

import com.videocean.exception.ClipException;
import com.videocean.exception.UserException;
import com.videocean.model.Clip;
import com.videocean.model.User;
import com.videocean.service.dao.ClipDAO;
import com.videocean.service.dao.UserDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.logging.Logger;

@Controller
public class SingleClipController {

    private Logger logger = Logger.getLogger(SingleClipController.class.getName());

    @RequestMapping(method = RequestMethod.GET, value = "/single-{id}")
    public String getSingleClipPage(@PathVariable("id") Integer id, Model viewModel, HttpServletRequest request,
                                    HttpServletResponse response) {
        Clip clip;
        List<Clip> clips;
        int likes = 0;
        int dislikes = 0;
        try {
            if (request.getSession().getAttribute("user") != null) {
                User user = (User) request.getSession().getAttribute("user");
                new UserDAO().addClipToHistory(id, user.getUserID());
            }
            clip = new ClipDAO().getClipByID(id);
            clips = new ClipDAO().getAllClips();
            likes = new UserDAO().getCountFromLikes(id, 1);
            dislikes = new UserDAO().getCountFromLikes(id, 2);
            viewModel.addAttribute("clip", clip);
            viewModel.addAttribute("clips", clips);
            viewModel.addAttribute("likes", likes);
            viewModel.addAttribute("dislikes", dislikes);
            clip.addViews();
            new ClipDAO().updateClip(clip);
            String url = "single-" + id;
            viewModel.addAttribute("currentUrl", url);
            // response.getWriter().write(url);
        } catch (ClipException | UserException e) {
            logger.info(e.getMessage());
            return "redirect:index:";
        }
        return "single";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/single-{id}/like")
    public @ResponseBody
    String addLikeToClip(@PathVariable("id") Integer id, Model viewModel,
                         HttpServletRequest request) {
        int likes = 0;
        int dislikes = 0;
        String stLikes = null;
        String stDislikes = null;
        UserDAO userDao = new UserDAO();
        updateUserLikes(id, viewModel, request, likes, userDao);
        try {
            likes = userDao.getCountFromLikes(id, 1);
            dislikes = userDao.getCountFromLikes(id, 2);

        } catch (UserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        stLikes = "" + likes;
        stDislikes = "" + dislikes;
        return (String) "{\"likes\":" + stLikes + ",\"dislikes\":" + stDislikes + "}";
    }

    private void updateUserLikes(@PathVariable("id") Integer id, Model viewModel, HttpServletRequest request, int likes, UserDAO userDao) {
        int state;
        try {
            if (request.getSession().getAttribute("user") != null) {
                User user = (User) request.getSession().getAttribute("user");
                state = userDao.getLikeIfExist(id, user.getUserID());
                if (state == 0) {
                    userDao.addLike(user.getUserID(), id, 1);
                }
                if (state == 1) {
                    userDao.deleteLike(user.getUserID(), id);
                }
                if (state == 2) {
                    userDao.updateLike(user.getUserID(), id, 1);
                }
                System.err.println(likes + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

            }
            viewModel.addAttribute("currentUrl", request.getRequestURL());

        } catch (UserException e) {
            // TODO Auto-generated catch block
            logger.info(e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/single-{id}/dislike")
    public @ResponseBody
    String addDislikeToClip(@PathVariable("id") Integer id, Model viewModel,
                            HttpServletRequest request) {
        int dislikes = 0;
        int likes = 0;
        String stLikes = null;
        System.out.println("INNNNNNNNNN");
        String stDislikes = null;
        UserDAO userDao = new UserDAO();
        updateDislikes(id, viewModel, request, dislikes, userDao);
        try {
            dislikes = userDao.getCountFromLikes(id, 2);
            likes = userDao.getCountFromLikes(id, 1);
        } catch (UserException e) {
            e.printStackTrace();
        }
        stLikes = "" + likes;
        stDislikes = "" + dislikes;
        return (String) "{\"dislikes\":" + stDislikes + ",\"likes\":" + stLikes + "}";
    }

    private void updateDislikes(@PathVariable("id") Integer id, Model viewModel, HttpServletRequest request, int dislikes, UserDAO userDao) {
        int state;
        try {
            if (request.getSession().getAttribute("user") != null) {
                User user = (User) request.getSession().getAttribute("user");
                state = userDao.getLikeIfExist(id, user.getUserID());
                if (state == 0) {
                    userDao.addLike(user.getUserID(), id, 2);
                }
                if (state == 2) {
                    userDao.deleteLike(user.getUserID(), id);
                }
                if (state == 1) {
                    userDao.updateLike(user.getUserID(), id, 2);
                }
                System.err.println(dislikes + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

            }
            viewModel.addAttribute("currentUrl", request.getRequestURL());

        } catch (UserException e) {
            // TODO Auto-generated catch block
            logger.info(e.getMessage());
        }
    }

}
