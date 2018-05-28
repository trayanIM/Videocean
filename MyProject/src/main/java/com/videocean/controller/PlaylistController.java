package com.videocean.controller;

import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.videocean.model.Clip;
import com.videocean.model.Playlist;
import com.videocean.service.dao.PlaylistDAO;
import com.videocean.exception.PlaylistException;
import com.videocean.model.User;

@Controller
public class PlaylistController {

	private Logger logger = Logger.getLogger(PlaylistController.class.getName());

	@RequestMapping(method = RequestMethod.GET, value = "/playlist")
	public String getPlaylistPage(Model viewModel, HttpServletRequest request) {
		if (request.getSession().getAttribute("user") == null) {
			return "error";
		}
		User user = (User) request.getSession().getAttribute("user");
		try {
			if (new PlaylistDAO().getPlaylistByOwner(user.getUserID()) != null) {
				Playlist playlist = new PlaylistDAO().getPlaylistByOwner(user.getUserID());
				System.out.println(playlist.getPlaylistID());
				playlist = new PlaylistDAO().getAllClipsForPlaylist(playlist.getPlaylistID());
				List<Clip> clips = playlist.getClips();
				Clip clip = clips.get(0);
				clips.remove(0);
				viewModel.addAttribute("clips", clips);
				viewModel.addAttribute("clip", clip);
			} else {
				System.out.println("else");
				return "redirect:index";
			}
		} catch (PlaylistException e) {
			logger.info(e.getMessage());
			return "redirect:history";
		}
		return "playlist";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/playlist-{id}")
	public String getPlaylistById(@PathVariable("id") Integer id, Model viewModel, HttpServletRequest request) {
		if (request.getSession().getAttribute("user") == null) {
			return "error";
		}
		User user = (User) request.getSession().getAttribute("user");
		try {
			if (new PlaylistDAO().getPlaylistByOwner(user.getUserID()) != null) {
				Playlist playlist = new PlaylistDAO().getPlaylistByOwner(user.getUserID());
				playlist = new PlaylistDAO().getAllClipsForPlaylist(playlist.getPlaylistID());
				List<Clip> clips = playlist.getClips();
				Clip clip = null;
				for (Clip forChange : clips) {
					if (forChange.getClipID() == id) {
						clip = forChange;
						break;
					}
				}
				clips.remove(clip);
				viewModel.addAttribute("clips", clips);
				viewModel.addAttribute("clip", clip);
			} else {
				System.out.println("else");
				return "redirect:index";
			}
		} catch (PlaylistException e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
			return "redirect:history";
		}
		return "playlist";
	}

}
