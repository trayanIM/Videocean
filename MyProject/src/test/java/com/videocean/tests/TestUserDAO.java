package com.videocean.tests;

import static org.junit.Assert.*;
import java.sql.SQLException;
import java.util.List;

import com.videocean.exception.ClipException;
import com.videocean.exception.PlaylistException;
import com.videocean.model.TYPE;
import com.videocean.service.dao.ClipDAO;
import com.videocean.service.dao.IPlaylistDAO;
import com.videocean.service.dao.PlaylistDAO;
import org.junit.Test;

import com.videocean.service.dbconnection.AbstractDAO;
import com.videocean.model.Clip;
import com.videocean.service.dbconnection.DBConnection;
import com.videocean.exception.PictureFormatException;
import com.videocean.model.Playlist;
import com.videocean.model.User;
import com.videocean.service.dao.UserDAO;
import com.videocean.exception.UserException;

public class TestUserDAO extends AbstractDAO {

	private static final String EMAIL_ADDRESS = "trayanI@abv.bg";
	private static final String PASSWORD = "123456789";
	private static final String FULL_NAME = "trayan muchev";
	private UserDAO userDAO = new UserDAO();
    private IPlaylistDAO playlistDAO = new PlaylistDAO();
    private ClipDAO clipDAO = new ClipDAO();

	@Test
	public void testConnection() {
		java.sql.Connection con = DBConnection.getInstance().getConnection();
		assertNotNull(con);
	}

	@Test
	public void testAddUser() throws UserException {
		int userId = userDAO.addUser(new User(EMAIL_ADDRESS, PASSWORD, FULL_NAME));
		User user = userDAO.getUserById(userId);
		assertNotNull(user);
		userDAO.removeUser(userId);
	}

	@Test
	public void testUpdateUser() throws UserException, PictureFormatException {
		int userId = userDAO.addUser(new User(EMAIL_ADDRESS, PASSWORD, FULL_NAME));
		User user = userDAO.getUserById(userId);
		user.setPassword("123456789");
		user.setLanguage("ENG");
		user.setBackgroundPicture("background.jpg");
		user.setPicture("picture.jpg");
		userDAO.updateUser(user);
		user = userDAO.getUserById(userId);
		assertNotNull(user);
		assertEquals("ENG", user.getLanguage());
		assertEquals("background.jpg", user.getBackgroundPicture());
		assertEquals("picture.jpg", user.getPicture());
		userDAO.removeUser(userId);
	}

	@Test
	public void testGetUserById() throws UserException, SQLException {
		int userId = userDAO.addUser(new User(EMAIL_ADDRESS, PASSWORD, FULL_NAME));
		User user = userDAO.getUserById(userId);
		assertNotNull(user);
		assertEquals(EMAIL_ADDRESS, user.getUsername());
		assertEquals(FULL_NAME, user.getFullName());
		userDAO.removeUser(userId);
	}

	@Test
	public void testGetUserByEmailAndPassword() throws UserException {
		int userId = userDAO.addUser(new User(EMAIL_ADDRESS, PASSWORD, FULL_NAME));
		User user1 = userDAO.getUserByEmailAndPass(EMAIL_ADDRESS, PASSWORD);
		assertNotNull(user1);
		userDAO.removeUser(userId);

	}

	@Test
	public void testGetUserByFullName() throws UserException {
		int userId = userDAO.addUser(new User(EMAIL_ADDRESS, PASSWORD, FULL_NAME));
		List<User> userList = userDAO.getUsersByName(FULL_NAME);
		assertNotNull(userList);
		assertTrue(userList.size() > 0);
		userDAO.removeUser(userId);
	}

	@Test
	public void testAllUsers() throws UserException {
        int userId = userDAO.addUser(new User(EMAIL_ADDRESS, PASSWORD, FULL_NAME));
		List<User> users = userDAO.getAllUsers();
		assertNotNull(users);
		assertTrue(users.size() > 0);
        userDAO.removeUser(userId);
	}

	@Test
	public void testAddPlaylist() throws UserException, PlaylistException {
        int userId = userDAO.addUser(new User(EMAIL_ADDRESS, PASSWORD, FULL_NAME));
        User user = userDAO.getUserById(userId);
        int playlistId = playlistDAO.createPlaylist(new Playlist("MyPlaylist", user, TYPE.PUBLIC));
		userDAO.addPlaylistIntoLibrary(playlistId, userId);
		userDAO.deletePlaylistFromLibrary(playlistId, playlistId);
		playlistDAO.removePlaylistByID(playlistId);
		userDAO.removeUser(userId);
	}

	@Test
	public void testGetAllPlaylists() throws UserException, PlaylistException {
        int userId = userDAO.addUser(new User(EMAIL_ADDRESS, PASSWORD, FULL_NAME));
        User user = userDAO.getUserById(userId);
        int playlistId = playlistDAO.createPlaylist(new Playlist("MyPlaylist", user, TYPE.PUBLIC));
        userDAO.addPlaylistIntoLibrary(playlistId, userId);
        List<Playlist> allPlaylistsForUser = userDAO.getAllPlaylistsForUser(userId);
        assertNotNull(allPlaylistsForUser);
        assertEquals(1, allPlaylistsForUser.size());
        playlistDAO.removePlaylistByID(playlistId);
        userDAO.removeUser(userId);
    }

	@Test
	public void testAddLike() throws UserException, ClipException {
        int userId = userDAO.addUser(new User(EMAIL_ADDRESS, PASSWORD, FULL_NAME));
        int clipId = clipDAO.addClip(new Clip("Name", userDAO.getUserById(userId), "URL", TYPE.PUBLIC));
        userDAO.addLike(userId, clipId, 1);
        int countLikes = userDAO.getCountFromLikes(clipId, 1);
        assertEquals(1, countLikes);
        userDAO.updateLike(userId, clipId, 2);
        int countDislikes = userDAO.getCountFromLikes(clipId, 2);
        assertEquals(1, countLikes);
        userDAO.deleteLike(userId, clipId);
        clipDAO.removeClip(clipId);
        userDAO.removeUser(userId);
	}

	@Test
	public void testAddHistory() throws UserException, ClipException {
        int userId = userDAO.addUser(new User(EMAIL_ADDRESS, PASSWORD, FULL_NAME));
        int clipId = clipDAO.addClip(new Clip("Name", userDAO.getUserById(userId), "URL", TYPE.PUBLIC));
		userDAO.addClipToHistory(clipId, userId);
        List<Clip> history = userDAO.getHistory(userId);
        assertNotNull(history);
        assertEquals(1, history.size());
        userDAO.deleteClipFromHistory(clipId, userId);
        clipDAO.removeClip(clipId);
        userDAO.removeUser(userId);
	}

}
