package com.example.tests;

import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import com.example.model.Category;
import com.example.model.CategoryDAO;
import com.example.model.CategoryException;
import com.example.model.Clip;
import com.example.model.ClipDAO;
import com.example.model.ClipException;
import com.example.model.TYPE;
import com.example.model.User;
import com.example.model.UserDAO;
import com.example.model.UserProblemException;

public class TestClipDAO {

	ClipDAO clipDAO = new ClipDAO();

	@Test
	public void testGetID() throws SQLException, ClipException, UserProblemException, CategoryException {
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
	public void testAddRemoveClip() throws ClipException, UserProblemException {
		int deleteThis;
		UserDAO user = new UserDAO();
		deleteThis = clipDAO.addClip(new Clip("DA", user.getUserById(1), "URL", TYPE.PUBLIC));
		clipDAO.removeClip(deleteThis);
	}

}
