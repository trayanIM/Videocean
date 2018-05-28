package com.example.controller;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.model.Clip;
import com.example.model.ClipDAO;
import com.example.model.ClipException;

@Controller
public class LoadSongController {
	@RequestMapping(method = RequestMethod.GET, value = "/load-{id}")
	public void showClientDetails(@PathVariable("id") Integer id, Model viewModel, HttpServletResponse response) {
		ClipDAO clipDao = new ClipDAO();
		Clip currentClip = null;
		String currentClipUri = null;
		File video = null;
		FileInputStream in;
		DataOutputStream out;
		try {
			currentClip = clipDao.getClipByID(id);
			currentClipUri = currentClip.getClipURL();
			video = new File(currentClipUri);
			response.setContentLength((int) video.length());
			in = new FileInputStream(video);
			out = new DataOutputStream(response.getOutputStream());
			byte[] buf = new byte[1024];
			int count = 0;
			while ((count = in.read(buf)) >= 0) {
				out.write(buf, 0, count);
			}
			out.flush();
			out.close();
			in.close();
		} catch (ClipException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Copy the contents of the file to the output stream

	}

}
