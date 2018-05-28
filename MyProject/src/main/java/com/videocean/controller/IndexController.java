package com.videocean.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.videocean.model.Clip;
import com.videocean.service.dao.ClipDAO;
import com.videocean.exception.ClipException;
import com.videocean.service.dao.SubscriptionFollowerDAO;
import com.videocean.model.User;
import com.videocean.exception.UserException;

@Controller
@RequestMapping(value = "/index")
public class IndexController {

	private Logger logger = Logger.getLogger(IndexController.class.getName());

	@RequestMapping(method = RequestMethod.GET)
	public String getIndexPage(Model viewModel, HttpServletRequest request) {
		int count = 0;
		request.getSession().setAttribute("count", 8);
		List<Clip> subscribeClips = new ArrayList<Clip>();
		ClipDAO clipDao = new ClipDAO();

		populateClipsForTheIndexPage(viewModel, count, clipDao);
		User user = (User) request.getSession().getAttribute("user");
		subscribeClips = populateSubscribeClips(subscribeClips, clipDao, user);

		viewModel.addAttribute("subClips", subscribeClips);
		viewModel.addAttribute("user", user);
		return "index";
	}

	private List<Clip> populateSubscribeClips(List<Clip> subscribeClips, ClipDAO clipDao, User user) {
		List<User> subscribers = new ArrayList<User>();;
		SubscriptionFollowerDAO subDao = new SubscriptionFollowerDAO();
		if (user != null) {
			try {
				subscribers = subDao.getSubscriptions(user.getUserID());
				for (User us : subscribers) {
					subscribeClips.addAll(clipDao.getAllClipsByOwnerId(us.getUserID()));
				}
			} catch (UserException | ClipException e) {
				logger.info(e.getMessage());
			}
		}
		if (subscribeClips.isEmpty()) {
			try {
				subscribeClips = clipDao.getAllClips();
			} catch (ClipException e) {
				logger.info(e.getMessage());
			}
		}
		List<Clip> rotate = new ArrayList<Clip>(subscribeClips);
		// Collections.reverse(subClips);

		subscribeClips.clear();
		for (int var = rotate.size() - 1; var >= 0; var--) {
			subscribeClips.add(rotate.get(var));
		}
		return subscribeClips;
	}

	private void populateClipsForTheIndexPage(Model viewModel, int count, ClipDAO clipDao) {
		try {
			List<Clip> forFirstPage = new ArrayList<Clip>();
			List<Clip> clips = clipDao.getAllClips();
			for (Clip clip : clips) {
				forFirstPage.add(clip);
				if (count == 7) {
					break;
				}
				count++;
			}
			viewModel.addAttribute("clips", forFirstPage);
		} catch (ClipException e) {
			logger.info("Clips not found in the DB!");
		}
	}

}
