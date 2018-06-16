package com.videocean.controller;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

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

	private Logger logger = Logger.getLogger(FileUploadController.class.getName());

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
			logger.info("Error while returning upload page!");
		}
		return "upload";
	}

	@RequestMapping(value = "/singleUpload", method = RequestMethod.POST)
	public String singleFileUpload(@Valid FileBucket fileBucket, BindingResult result, ModelMap model,
			HttpServletRequest request) throws IOException {

		String fileSufix = fileBucket.getFile().getOriginalFilename().substring(
				fileBucket.getFile().getOriginalFilename().length() - 3,
				fileBucket.getFile().getOriginalFilename().length());
		logger.info("File sufix is " + fileSufix);
		if (fileSufix.equals("mp4") || fileSufix.equals("ogg")) {
			if (result.hasErrors()) {
				logger.info("Errors during clip validation!");
				return "upload";
			} else {
				logger.info("Fetching file!");
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
						logger.info(e.getMessage());
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
					request.setAttribute("user", user);
				} catch (PlaylistException | UserException | ClipException e) {
					// TODO Auto-generated catch block
					String error = "The video you are trying to upload is already existing!";
					logger.info(error);
					model.addAttribute("error", error);
					return "upload";

				}
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
			String error = "Wrong video format! The supported video formats are mp4 and ogg!";
			model.addAttribute("error", error);
			return "upload";
		}
	}

}