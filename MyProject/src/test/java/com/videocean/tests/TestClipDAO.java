package com.videocean.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;

import com.videocean.model.User;
import org.junit.After;
import org.junit.Before;
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

    private int userId;

    private ClipDAO clipDAO = new ClipDAO();
    private UserDAO userDAO = new UserDAO();

    @Before
    public void setUp() throws UserException {
        User user = new User("trayanI@gmail.com", "1234567", "Muchev");
        this.userId = userDAO.addUser(user);
    }

//    @Test
//    public void delete() throws SQLException, ClipException, UserException, CategoryException {
//        clipDAO.removeClip(159);
//    }

    @After
    public void tearDown() throws UserException {
        userDAO.removeUser(userId);
    }

    @Test
    public void testGetById() throws SQLException, ClipException, UserException, CategoryException {
        int clipId = clipDAO.addClip(new Clip("Name", userDAO.getUserById(userId), "URL", TYPE.PUBLIC));
        Clip clip = clipDAO.getClipByID(clipId);
        assertNotNull(clip);
        assertEquals("Name", clip.getName());
        assertEquals("URL", clip.getClipURL());
        assertEquals(userId, clip.getOwner().getUserID());
        clipDAO.removeClip(clipId);
    }

    @Test
    public void testGetAll() throws SQLException, ClipException, UserException {
        int clipId = clipDAO.addClip(new Clip("Name", userDAO.getUserById(userId), "URL", TYPE.PUBLIC));
        List<Clip> clips = clipDAO.getAllClips();
        assertTrue(clips.size() > 0);
        for (Clip c : clips) {
            assertNotNull(c);
        }
        clipDAO.removeClip(clipId);
    }

    @Test
    public void testAddRemoveClip() {
        try {
            int clipToDeleteId = clipDAO.addClip(new Clip("DA", userDAO.getUserById(userId), "URL", TYPE.PUBLIC));
            clipDAO.removeClip(clipToDeleteId);
            Clip clip = clipDAO.getClipByID(clipToDeleteId);
        } catch (ClipException | UserException e) {
            assertEquals("Clip with the specified Id does not exist!", e.getMessage());
        }
    }

}
