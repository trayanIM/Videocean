package com.example.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.model.Clip;
import com.example.model.ClipDAO;
import com.example.model.ClipException;
import com.example.model.User;
import com.example.model.UserDAO;
import com.example.model.UserProblemException;

@Controller
public class singleClipController {

	@RequestMapping(method = RequestMethod.GET, value = "/single-{id}")
	public String showClientDetails(@PathVariable("id") Integer id, Model viewModel, HttpServletRequest request,
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
		} catch (ClipException | UserProblemException e) {
			e.printStackTrace();
			System.out.println("nyama takav clip");
			return "redirect:index:";
		}
		return "single";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/single-{id}/like")
	public @ResponseBody String addLikeToClip(@PathVariable("id") Integer id, Model viewModel,
			HttpServletRequest request) {
		int likes = 0;
		int dislikes = 0;
		System.out.println("INNNNNNNNNN");
		String stLikes = null;
		String stDislikes = null;
		UserDAO userDao = null;
		int state;
		try {
			if (request.getSession().getAttribute("user") != null) {
				User user = (User) request.getSession().getAttribute("user");
				userDao = new UserDAO();
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

		} catch (UserProblemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("nyama takav clip");

		}
		try {
			likes = userDao.getCountFromLikes(id, 1);
			dislikes = userDao.getCountFromLikes(id, 2);

		} catch (UserProblemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stLikes = "" + likes;
		stDislikes = "" + dislikes;
		return (String) "{\"likes\":" + stLikes + ",\"dislikes\":" + stDislikes + "}";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/single-{id}/dislike")
	public @ResponseBody String addDislikeToClip(@PathVariable("id") Integer id, Model viewModel,
			HttpServletRequest request) {
		int dislikes = 0;
		int likes = 0;
		String stLikes = null;
		System.out.println("INNNNNNNNNN");
		String stDislikes = null;
		UserDAO userDao = null;
		int state;
		try {
			if (request.getSession().getAttribute("user") != null) {
				User user = (User) request.getSession().getAttribute("user");
				userDao = new UserDAO();
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

		} catch (UserProblemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("nyama takav clip");

		}
		try {
			dislikes = userDao.getCountFromLikes(id, 2);
			likes = userDao.getCountFromLikes(id, 1);
		} catch (UserProblemException e) {
			e.printStackTrace();
		}
		stLikes = "" + likes;
		stDislikes = "" + dislikes;
		return (String) "{\"dislikes\":" + stDislikes + ",\"likes\":" + stLikes + "}";
	}

}
