package com.videocean.tests;

import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import com.videocean.service.dao.CategoryDAO;
import com.videocean.exception.CategoryException;
import com.videocean.model.Clip;
import com.videocean.service.dao.ClipDAO;
import com.videocean.exception.ClipException;
import com.videocean.model.TYPE;
import com.videocean.service.dao.UserDAO;
import com.videocean.exception.UserException;

public class TestClipDAO {

	ClipDAO clipDAO = new ClipDAO();

	@Test
	public void testGetID() throws SQLException, ClipException, UserException, CategoryException {
		Clip clip = clipDAO.getClipByID(1);
		assertNotNull(clip);
		clip.setClipID(1);
		clip.setDescription("wooooow");
		clip.setViews(10000);
		clip.setCategory(new CategoryDAO().getCategoryByID(1));
		clipDAO.updateClip(clip);
	}

	@Test
	public void testGetAll() throws SQLException, ClipException {
		List<Clip> clips = clipDAO.getAllClips();
		for (Clip c : clips) {
			assertNotNull(c);
		}
	}

	@Test
	public void testAddRemoveClip() throws ClipException, UserException {
		int deleteThis;
		UserDAO user = new UserDAO();
		deleteThis = clipDAO.addClip(new Clip("DA", user.getUserById(1), "URL", TYPE.PUBLIC));
		clipDAO.removeClip(deleteThis);
	}

}
