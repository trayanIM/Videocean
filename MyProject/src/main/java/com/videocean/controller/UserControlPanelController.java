package com.videocean.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.videocean.model.Clip;
import com.videocean.service.dao.ClipDAO;
import com.videocean.exception.ClipException;
import com.videocean.service.dao.SubscriptionFollowerDAO;
import com.videocean.model.User;
import com.videocean.service.dao.UserDAO;
import com.videocean.exception.UserException;

@Controller
public class UserControlPanelController {

	private Logger logger = Logger.getLogger(UserControlPanelController.class.getName());

	@RequestMapping(method = RequestMethod.GET, value = "/user")
	public String getUserPage(Model viewModel, HttpServletRequest request) {
		if (request.getSession().getAttribute("user") == null) {
			return "error";
		}
		int likesCount = 0;
		List<User> followers = new ArrayList<User>();
		int count = 0;
		User user = (User) request.getSession().getAttribute("user");
		viewModel.addAttribute("user", user);
		UserDAO userDao = new UserDAO();
		try {
			followers = new SubscriptionFollowerDAO().getFollowers(user.getUserID());
			if (!followers.isEmpty()) {
				count = followers.size();
			}
		} catch (UserException e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
		}

		try {
			viewModel.addAttribute("follow", count);
			int views = userDao.countTheViewsOfThisUserClips(user);
			List<Clip> clipFromPlaylist = new ClipDAO().getAllClipsByOwnerId(user.getUserID());
			List<Clip> forUserPage = new ArrayList<Clip>();
			for (int max = clipFromPlaylist.size() - 1; max > clipFromPlaylist.size() - 4 && max >= 0; max--) {
				forUserPage.add(clipFromPlaylist.get(max));
				System.out.println(clipFromPlaylist.get(max).getClipID());
			}

			ClipDAO clipDao = new ClipDAO();
			List<Clip> clipsForThisUser = clipDao.getAllClipsByOwnerId(user.getUserID());
			for (Clip clip : clipsForThisUser) {
				likesCount += userDao.getCountFromLikes(clip.getClipID(), 1);
			}

			viewModel.addAttribute("likesOfClip", likesCount);
			viewModel.addAttribute("clips", forUserPage);
			if (views > 0) {
				viewModel.addAttribute("views", views);
			} else {
				views = 0;
				viewModel.addAttribute("views", views);
			}

			viewModel.addAttribute("currentURL", "user-" + user.getUserID());
			return "userControlPanel";
		} catch (UserException | ClipException e) {
			logger.info(e.getMessage());
			return "redirect:index";
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/user-{id}")
	public String getOtherUserPage(@PathVariable("id") Integer id, Model viewModel, HttpServletRequest request) {
		if (request.getSession().getAttribute("user") == null) {
			return "error";
		}
		int likesCount = 0;
		List<User> followers = new ArrayList<User>();
		int count = 0;
		try {
			UserDAO userDao = new UserDAO();
			User user = userDao.getUserById(id);
			viewModel.addAttribute("user", user);

			try {
				followers = new SubscriptionFollowerDAO().getFollowers(id);
				if (!followers.isEmpty()) {
					count = followers.size();
				}
			} catch (UserException e) {
				// TODO Auto-generated catch block
				logger.info(e.getMessage());
			}
			viewModel.addAttribute("follow", count);
			int views = userDao.countTheViewsOfThisUserClips(user);
			List<Clip> clipFromPlaylist = new ClipDAO().getAllClipsByOwnerId(user.getUserID());
			List<Clip> forUserPage = new ArrayList<Clip>();
			for (int max = clipFromPlaylist.size() - 1; max > clipFromPlaylist.size() - 4 && max >= 0; max--) {
				forUserPage.add(clipFromPlaylist.get(max));
				System.out.println(clipFromPlaylist.get(max).getClipID());
			}

			ClipDAO clipDao = new ClipDAO();
			List<Clip> clipsForThisUser = clipDao.getAllClipsByOwnerId(id);
			for (Clip clip : clipsForThisUser) {
				likesCount += userDao.getCountFromLikes(clip.getClipID(), 1);
			}

			viewModel.addAttribute("likesOfClip", likesCount);

			viewModel.addAttribute("clips", forUserPage);
			if (views > 0) {
				viewModel.addAttribute("views", views);
			} else {
				views = 0;
				viewModel.addAttribute("views", views);
			}

			viewModel.addAttribute("currentURL", "user-" + id);
			return "userControlPanel";
		} catch (UserException | ClipException e) {
			logger.info(e.getMessage());
			return "redirect:index";
		}

	}

	@RequestMapping(method = RequestMethod.POST, value = "/user-{id}/follow")
	public @ResponseBody String followClip(@PathVariable("id") Integer id, Model viewModel,
										   HttpServletRequest request) {
		int follow = 0;
		String stFollow = null;
		SubscriptionFollowerDAO subDao = null;
		List<User> foll = new ArrayList<User>();
		int state;
		User user = null;
		try {
			if (request.getSession().getAttribute("user") != null) {
				user = (User) request.getSession().getAttribute("user");
				if (user.getUserID() != id) {
					subDao = new SubscriptionFollowerDAO();
					state = subDao.getState(user.getUserID(), id);
					if (state == 0) {
						subDao.addSubscriptionFollower(user.getUserID(), id);
					} else {
						subDao.deleteSubscription(user.getUserID(), id);
					}
				}
			}
			viewModel.addAttribute("currentURL", request.getRequestURL());

		} catch (UserException e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
		}
		try {
			foll = subDao.getFollowers(id);
			if (!foll.isEmpty()) {
				follow = foll.size();
			}

		} catch (UserException e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
		}
		stFollow = "" + follow;
		return (String) "{\"follow\":" + stFollow + "}";
	}

}