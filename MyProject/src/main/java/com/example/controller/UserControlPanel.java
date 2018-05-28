package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.model.Clip;
import com.example.model.ClipDAO;
import com.example.model.ClipException;
import com.example.model.Playlist;
import com.example.model.PlaylistDAO;
import com.example.model.PlaylistException;
import com.example.model.SubscriptionFollowerDAO;
import com.example.model.User;
import com.example.model.UserDAO;
import com.example.model.UserProblemException;

@Controller
public class UserControlPanel {

	@RequestMapping(method = RequestMethod.GET, value = "/user")
	public String userControl(Model viewModel, HttpServletRequest request) {
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
		} catch (UserProblemException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
		} catch (UserProblemException | ClipException e) {

			e.printStackTrace();
			return "redirect:index";
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/user-{id}")
	public String otherUser(@PathVariable("id") Integer id, Model viewModel, HttpServletRequest request) {
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
			} catch (UserProblemException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
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
		} catch (UserProblemException | ClipException e) {

			e.printStackTrace();
			return "redirect:index";
		}

	}

	@RequestMapping(method = RequestMethod.POST, value = "/user-{id}/follow")
	public @ResponseBody String addLikeToClip(@PathVariable("id") Integer id, Model viewModel,
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

		} catch (UserProblemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("nyama takav clip");

		}
		try {
			foll = subDao.getFollowers(id);
			if (!foll.isEmpty()) {
				follow = foll.size();
			}

		} catch (UserProblemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stFollow = "" + follow;
		return (String) "{\"follow\":" + stFollow + "}";
	}

}