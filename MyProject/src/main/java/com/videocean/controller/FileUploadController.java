package com.videocean.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.videocean.exception.CategoryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.videocean.model.Category;
import com.videocean.service.dao.CategoryDAO;
import com.videocean.model.Clip;
import com.videocean.service.dao.ClipDAO;
import com.videocean.exception.ClipException;
import com.videocean.service.file.FileBucket;
import com.videocean.service.file.FileValidator;
import com.videocean.model.Playlist;
import com.videocean.service.dao.PlaylistDAO;
import com.videocean.exception.PlaylistException;
import com.videocean.model.TYPE;
import com.videocean.model.User;
import com.videocean.service.dao.UserDAO;
import com.videocean.exception.UserException;

@Controller
public class FileUploadController {

	private static String UPLOAD_LOCATION = "E:/VideoceanFiles/";

	@Autowired
	FileValidator fileValidator;

	@InitBinder("fileBucket")
	protected void initBinderFileBucket(WebDataBinder binder) {
		binder.setValidator(fileValidator);
	}

	@RequestMapping(value = "/singleUpload", method = RequestMethod.GET)
	public String getSingleUploadPage(ModelMap model, HttpServletRequest request) {

		if (request.getSession().getAttribute("user") == null) {
			return "error";
		}

		FileBucket fileModel = new FileBucket();
		model.addAttribute("fileBucket", fileModel);

		CategoryDAO categoryes = new CategoryDAO();
		try {
			model.addAttribute("categories", categoryes.getAllCategories());
		} catch (CategoryException e) {
			e.printStackTrace();
		}
		return "upload";
	}

	@RequestMapping(value = "/singleUpload", method = RequestMethod.POST)
	public String singleFileUpload(@Valid FileBucket fileBucket, BindingResult result, ModelMap model,
			HttpServletRequest request) throws IOException {

		String check = fileBucket.getFile().getOriginalFilename().substring(
				fileBucket.getFile().getOriginalFilename().length() - 3,
				fileBucket.getFile().getOriginalFilename().length());
		System.out.println(check);
		if (check.equals("mp4") || check.equals("ogg")) {
			if (result.hasErrors()) {
				System.out.println("validation errors");
				return "upload";
			} else {
				System.out.println("Fetching file");
				MultipartFile multipartFile = fileBucket.getFile();
				User user = (User) request.getSession().getAttribute("user");
				UserDAO usDAO = new UserDAO();
				PlaylistDAO plDAO = new PlaylistDAO();
				Playlist playlist;
				int playlistId;
				int clipId;
				Clip clip;
				String path = null;
				System.out.println(fileBucket.getFile().getOriginalFilename());
				for (int count = fileBucket.getFile().getOriginalFilename().length() - 1; count > 0; count--) {
					if (fileBucket.getFile().getOriginalFilename().charAt(count) == '\\') {
						path = fileBucket.getFile().getOriginalFilename().substring(count + 1,
								fileBucket.getFile().getOriginalFilename().length());
						break;
					}
				}
				System.out.println(fileBucket.getName());
				try {
					if (path != null) {
						clip = new Clip(fileBucket.getName(), user, UPLOAD_LOCATION + path, TYPE.PUBLIC);
					} else {
						clip = new Clip(fileBucket.getName(), user,
								UPLOAD_LOCATION + fileBucket.getFile().getOriginalFilename(), TYPE.PUBLIC);
					}

					CategoryDAO category = new CategoryDAO();
					try {
						Category thisCategory = category.getCategoryByID(fileBucket.getCategory());
						clip.setCategory(thisCategory);
					} catch (CategoryException e) {
						e.printStackTrace();
					}

					clip.setDescription(fileBucket.getDescription());
					clipId = new ClipDAO().addClip(clip);
					clip.setClipID(clipId);
					if (plDAO.getPlaylistByOwner(user.getUserID()) == null) {
						playlist = new Playlist("My Clips", user, TYPE.PUBLIC);
						playlistId = plDAO.createPlaylist(playlist);
						usDAO.addPlaylistIntoLibrary(playlistId, user.getUserID());
					} else {
						playlist = plDAO.getPlaylistByOwner(user.getUserID());
						playlistId = playlist.getPlaylistID();
					}
					plDAO.addClipToPlaylist(playlistId, clipId);
					// user.addClipToMyClips(clip);
					// user.getMyClips().setPlaylistID(playlistId);
					request.setAttribute("user", user);
				} catch (PlaylistException | UserException | ClipException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					String error = "There is already such a video";
					model.addAttribute("error", error);
					return "upload";

				}
				// Now do something with file...
				System.out.println("do tuk e dobre");
				if (path != null) {
					FileCopyUtils.copy(fileBucket.getFile().getBytes(), new File(UPLOAD_LOCATION + path));
				} else {
					FileCopyUtils.copy(fileBucket.getFile().getBytes(),
							new File(UPLOAD_LOCATION + fileBucket.getFile().getOriginalFilename()));
				}
				String fileName = multipartFile.getOriginalFilename();
				model.addAttribute("fileName", fileName);
				return "redirect:index";
			}
		} else {
			String error = "Wrong video format";
			model.addAttribute("error", error);
			return "upload";
		}
	}

}