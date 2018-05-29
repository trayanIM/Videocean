package com.videocean.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.videocean.model.Clip;
import com.videocean.service.dao.ClipDAO;
import com.videocean.exception.ClipException;
import com.videocean.service.dao.IPlaylistDAO;
import com.videocean.model.Playlist;
import com.videocean.service.dao.PlaylistDAO;
import com.videocean.exception.PlaylistException;
import com.videocean.model.TYPE;
import com.videocean.model.User;
import com.videocean.service.dao.UserDAO;
import com.videocean.exception.UserException;

public class TestPlaylistDAO {

	private ClipDAO clipDAO = new ClipDAO();
	private UserDAO userDAO = new UserDAO();
	private IPlaylistDAO playlistDAO = new PlaylistDAO();
	private int userId;
	private int clipId;

	@Before
	public void setUp() throws UserException, ClipException {
		User user = new User("trayanI@gmail.com", "1234567", "Muchev");
		userId = userDAO.addUser(user);
		clipId = clipDAO.addClip(new Clip("Name", userDAO.getUserById(userId), "URL", TYPE.PUBLIC));
	}

	@After
	public void tearDown() throws UserException, ClipException {
		clipDAO.removeClip(clipId);
		userDAO.removeUser(userId);
	}

	@Test
	public void testCreatePlaylist() throws UserException, PlaylistException {
		User user = userDAO.getUserById(userId);
		Playlist playlist = new Playlist("MyPlaylist", user, TYPE.PUBLIC);
		int playlistId = playlistDAO.createPlaylist(playlist);
		Playlist playlistById = playlistDAO.getPlaylistById(playlistId);
		assertNotNull(playlistById);
		playlistDAO.removePlaylistByID(playlistId);
	}

	@Test
	public void testAddAndRemoveClipToPlayList() throws ClipException, PlaylistException, SQLException, UserException {
		User user = userDAO.getUserById(userId);
		Playlist playlist = new Playlist("MyPlaylist", user, TYPE.PUBLIC);
		int playlistId = playlistDAO.createPlaylist(playlist);
		Clip clip = clipDAO.getClipByID(clipId);
		//add clip in playlist
		playlistDAO.addClipToPlaylist(playlistId, clip.getClipID());
		Playlist playlistById = playlistDAO.getAllClipsForPlaylist(playlistId);
		assertNotNull(playlistById);
		assertNotNull(playlistById.getClips());
		assertEquals(1, playlistById.getClips().size());
		//remove clip from playlist
		playlistDAO.removeClipFromPlaylist(playlistId, clipId);
		playlistById = playlistDAO.getAllClipsForPlaylist(playlistId);
		assertNotNull(playlistById);
		assertNull(playlistById.getClips());
		playlistDAO.removePlaylistByID(playlistId);
	}

	@Test
	public void testIncreaseViewsOfPlaylist() throws PlaylistException, UserException {
		User user = userDAO.getUserById(userId);
		int playlistId = playlistDAO.createPlaylist(new Playlist("MyPlaylist", user, TYPE.PUBLIC));
		Playlist playlist = playlistDAO.getPlaylistById(playlistId);
		playlistDAO.increaseViewsOfPlaylist(playlist);
		playlist = playlistDAO.getPlaylistById(playlistId);
		assertNotNull(playlist);
		assertEquals(1, playlist.getViewsOfPlaylist());
		playlistDAO.removePlaylistByID(playlistId);
	}

	@Test
	public void testGetPlaylistById() throws PlaylistException, UserException {
		User user = userDAO.getUserById(userId);
		Playlist playlist = new Playlist("MyPlaylist", user, TYPE.PUBLIC);
		int playlistId = playlistDAO.createPlaylist(playlist);
		Playlist playlistById = playlistDAO.getPlaylistById(playlistId);
		assertNotNull(playlistById);
		assertEquals("MyPlaylist", playlistById.getName());
		assertEquals(userId, playlistById.getOwner().getUserID());
		playlistDAO.removePlaylistByID(playlistId);
	}
}
