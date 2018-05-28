package com.videocean.tests;

import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
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

	@Test
	public void creatPlaylist() throws UserException, PlaylistException {
		UserDAO userDAO = new UserDAO();
		User user = userDAO.getUserById(1);
		IPlaylistDAO playlistDAO = new PlaylistDAO();
		Playlist playlist = new Playlist("MoqPlaylist", user, TYPE.PUBLIC);
		int id = playlistDAO.createPlaylist(playlist);
		playlistDAO.removePlaylistByID(id);
	}

	@Test
	public void addOrRemoveClipToPlayList() throws ClipException, PlaylistException, SQLException {
		ClipDAO clipDAO = new ClipDAO();
		Clip clip = clipDAO.getClipByID(1);
		IPlaylistDAO playlistDAO = new PlaylistDAO();
		playlistDAO.addClipToPlaylist(1, clip.getClipID());
		playlistDAO.removeClipFromPlaylist(1, 1);
	}

	@Test
	public void increaseViewsOfPlaylist() throws PlaylistException {
		IPlaylistDAO playlistDAO = new PlaylistDAO();
		Playlist playlist = playlistDAO.getPlaylistById(1);
		playlistDAO.increaseViewsOfPlaylist(playlist);
	}

	@Test
	public void getPlaylistById() throws PlaylistException {
		PlaylistDAO playlistDAO = new PlaylistDAO();
		Playlist newPlaylist = playlistDAO.getPlaylistById(1);
		assertNotNull(newPlaylist);
	}
}
