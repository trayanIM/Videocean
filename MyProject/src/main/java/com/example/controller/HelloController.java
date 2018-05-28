package com.example.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.model.Clip;
import com.example.model.ClipDAO;
import com.example.model.ClipException;
import com.example.model.SubscriptionFollowerDAO;
import com.example.model.User;
import com.example.model.UserProblemException;

@Controller
@RequestMapping(value = "/index")
public class HelloController {

	@RequestMapping(method = RequestMethod.GET)
	public String sayHello(Model viewModel, HttpServletRequest request) {
		int count = 0;
		request.getSession().setAttribute("count", 8);
		List<Clip> subClips = new ArrayList<Clip>();
		List<User> sub = new ArrayList<User>();
		SubscriptionFollowerDAO subDao = new SubscriptionFollowerDAO();
		ClipDAO clipDao = new ClipDAO();
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
			e.printStackTrace();
			System.out.println("Nyama Klipove");
		}

		User user = (User) request.getSession().getAttribute("user");
		if (user != null) {
			try {
				sub = subDao.getSubscriptions(user.getUserID());
				for (User us : sub) {
					subClips.addAll(clipDao.getAllClipsByOwnerId(us.getUserID()));
				}
			} catch (UserProblemException | ClipException e) {
				e.printStackTrace();
			}
		}
		if (subClips.isEmpty()) {
			try {
				subClips = clipDao.getAllClips();
			} catch (ClipException e) {
				e.printStackTrace();
			}
		}
		List<Clip> rotate = new ArrayList<Clip>(subClips);
		// Collections.reverse(subClips);

		subClips.clear();
		for (int var = rotate.size() - 1; var >= 0; var--) {
			subClips.add(rotate.get(var));
		}
		viewModel.addAttribute("subClips", subClips);
		viewModel.addAttribute("user", user);
		return "index";
	}

}
