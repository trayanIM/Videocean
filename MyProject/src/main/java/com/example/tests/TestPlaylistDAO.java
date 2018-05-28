package com.example.tests;

import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import org.junit.Test;

import com.example.model.Clip;
import com.example.model.ClipDAO;
import com.example.model.ClipException;
import com.example.model.IPlaylistDAO;
import com.example.model.Playlist;
import com.example.model.PlaylistDAO;
import com.example.model.PlaylistException;
import com.example.model.TYPE;
import com.example.model.User;
import com.example.model.UserDAO;
import com.example.model.UserProblemException;

public class TestPlaylistDAO {

	@Test
	public void creatPlaylist() throws UserProblemException, PlaylistException {
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
