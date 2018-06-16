package com.videocean.controller;

import com.videocean.exception.CategoryException;
import com.videocean.exception.ClipException;
import com.videocean.exception.PlaylistException;
import com.videocean.exception.UserException;
import com.videocean.model.*;
import com.videocean.service.dao.CategoryDAO;
import com.videocean.service.dao.ClipDAO;
import com.videocean.service.dao.PlaylistDAO;
import com.videocean.service.dao.UserDAO;
import com.videocean.service.file.FileBucket;
import com.videocean.service.file.FileValidator;
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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@Controller
public class FileUploadController {

    private static final int MAX_NUMBER_OF_CLIPS = 20;
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

        CategoryDAO categories = new CategoryDAO();
        try {
            model.addAttribute("categories", categories.getAllCategories());
        } catch (CategoryException e) {
            logger.info("Error while returning upload page!");
        }
        return "upload";
    }

    @RequestMapping(value = "/singleUpload", method = RequestMethod.POST)
    public String singleFileUpload(@Valid FileBucket fileBucket, BindingResult result, ModelMap model,
                                   HttpServletRequest request) throws IOException, CategoryException, ClipException {

        if (!isVideoValid(fileBucket, result, model, request)) {
            return "upload";
        }
        logger.info("Fetching file!");
        MultipartFile multipartFile = fileBucket.getFile();
        User user = (User) request.getSession().getAttribute("user");
        UserDAO usDAO = new UserDAO();
        PlaylistDAO plDAO = new PlaylistDAO();
        int playlistId;
        int clipId;
        Clip clip;

        String path = getFilePath(fileBucket);

        try {
            clip = getClip(fileBucket, user, path);
            CategoryDAO category = new CategoryDAO();
            clip.setCategory(getClipCategory(fileBucket, category));
            clip.setDescription(fileBucket.getDescription());
            clipId = new ClipDAO().addClip(clip);
            clip.setClipID(clipId);
            playlistId = getUserPlaylist(user, usDAO, plDAO);
            plDAO.addClipToPlaylist(playlistId, clipId);
            request.setAttribute("user", user);
        } catch (PlaylistException | UserException | ClipException e) {
            result.rejectValue("file", "video.exist");
            model.addAttribute("categories", getCategories());
            return "upload";
        }

        saveClipInSystem(fileBucket, path);
        String fileName = multipartFile.getOriginalFilename();
        model.addAttribute("fileName", fileName);
        return "redirect:index";
    }

    private void saveClipInSystem(@Valid FileBucket fileBucket, String path) throws IOException {
        if (path != null) {
            FileCopyUtils.copy(fileBucket.getFile().getBytes(), new File(UPLOAD_LOCATION + path));
        } else {
            FileCopyUtils.copy(fileBucket.getFile().getBytes(),
                    new File(UPLOAD_LOCATION + fileBucket.getFile().getOriginalFilename()));
        }
    }

    private int getUserPlaylist(User user, UserDAO usDAO, PlaylistDAO plDAO) throws PlaylistException, UserException {
        Playlist playlist;
        int playlistId;
        if (plDAO.getPlaylistByOwner(user.getUserID()) == null) {
            playlist = new Playlist("My Clips", user, TYPE.PUBLIC);
            playlistId = plDAO.createPlaylist(playlist);
            usDAO.addPlaylistIntoLibrary(playlistId, user.getUserID());
        } else {
            playlist = plDAO.getPlaylistByOwner(user.getUserID());
            playlistId = playlist.getPlaylistID();
        }
        return playlistId;
    }

    private Category getClipCategory(@Valid FileBucket fileBucket, CategoryDAO category) throws CategoryException {
        try {
            return category.getCategoryByID(fileBucket.getCategory());
        } catch (CategoryException e) {
            logger.info(e.getMessage());
            throw e;
        }
    }

    private Clip getClip(@Valid FileBucket fileBucket, User user, String path) throws ClipException {
        Clip clip;
        if (path != null) {
            clip = new Clip(fileBucket.getName(), user, UPLOAD_LOCATION + path, TYPE.PUBLIC);
        } else {
            clip = new Clip(fileBucket.getName(), user,
                    UPLOAD_LOCATION + fileBucket.getFile().getOriginalFilename(), TYPE.PUBLIC);
        }
        return clip;
    }

    private String getFilePath(@Valid FileBucket fileBucket) {
        String path = null;
        for (int count = fileBucket.getFile().getOriginalFilename().length() - 1; count > 0; count--) {
            if (fileBucket.getFile().getOriginalFilename().charAt(count) == '\\') {
                path = fileBucket.getFile().getOriginalFilename().substring(count + 1,
                        fileBucket.getFile().getOriginalFilename().length());
                break;
            }
        }
        return path;
    }

    private boolean isVideoValid(FileBucket fileBucket, BindingResult result, ModelMap model,
                                 HttpServletRequest request) throws CategoryException, ClipException {
        boolean flag = true;
        ClipDAO clipDAO = new ClipDAO();
        User user = (User) request.getSession().getAttribute("user");
        List<Category> categories = getCategories();
        List<Clip> clips = clipDAO.getAllClipsByOwnerId(user.getUserID());

        if (result.hasErrors()) {
            flag = false;
        } else if (clips != null && clips.size() > MAX_NUMBER_OF_CLIPS) {
            result.rejectValue("file", "max.clips");
            flag = false;
        } else {
            String fileSufix = fileBucket.getFile().getOriginalFilename().substring(
                    fileBucket.getFile().getOriginalFilename().length() - 3,
                    fileBucket.getFile().getOriginalFilename().length());
            if (!fileSufix.equalsIgnoreCase("mp4") && !fileSufix.equalsIgnoreCase("ogg")) {
                result.rejectValue("file", "unsupported.format");
                flag = false;
            }
        }

        if (flag == false) {
            model.addAttribute("categories", categories);
        }
        return flag;
    }

    private List<Category> getCategories() throws CategoryException {
        CategoryDAO categoryDAO = new CategoryDAO();
        return categoryDAO.getAllCategories();
    }

}