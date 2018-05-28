package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.model.Clip;
import com.example.model.ClipDAO;
import com.example.model.ClipException;
import com.example.model.Playlist;
import com.example.model.PlaylistDAO;
import com.example.model.PlaylistException;
import com.example.model.User;
import com.example.model.UserDAO;
import com.example.model.UserProblemException;

@Controller
public class PlaylistController {

	@RequestMapping(method = RequestMethod.GET, value = "/playlist")
	public String showClientDetails(Model viewModel, HttpServletRequest request) {
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
			e.printStackTrace();
			System.out.println("greshka");
			return "redirect:history";
		}
		return "playlist";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/playlist-{id}")
	public String showClientDetails(@PathVariable("id") Integer id, Model viewModel, HttpServletRequest request) {
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
			e.printStackTrace();
			System.out.println("greshka");
			return "redirect:history";
		}
		return "playlist";
	}

}
