package com.videocean.tests;

import static org.junit.Assert.*;
import java.sql.SQLException;
import java.util.List;
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
	UserDAO user = new UserDAO();

	@Test
	public void testConnection() {
		java.sql.Connection con = DBConnection.getInstance().getConnection();
		assertNotNull(con);
	}

	@Test
	public void testAddUser() throws UserException {
		int forDelete;
		forDelete = user.addUser(new User("mrayan@abv.bg", "123456789", "trayan muchev"));
		user.removeUser(forDelete);
	}

	@Test
	public void testUpdateUser() throws UserException, PictureFormatException {
		User us = new User(1, "tdra@abv.bg", "stoina");
		us.setPassword("1232445436");
		us.setLanguage("ENG");
		us.setBackgroundPicture("neshto.jpg");
		us.setPicture("picture.jpg");
		user.updateUser(us);
	}

	@Test
	public void testGetUserById() throws UserException, SQLException {
		User us = user.getUserById(1);
		assertNotNull(us);
	}

	@Test
	public void testEmailAndName() throws UserException {
		User user1 = user.getUserByEmailAndPass("tdra@abv.bg", "1232445436");
		assertNotNull(user1);
	}

	@Test
	public void testFullName() throws UserException {
		List<User> users = user.getUsersByName("stoina");
		for (User u : users) {
			assertNotNull(u);
		}
	}

	@Test
	public void testAllUsers() throws UserException {
		List<User> users = user.getAllUsers();
		for (User u : users) {
			assertNotNull(u);
		}
	}

	@Test
	public void testAddPlaylist() throws UserException {
		user.addPlaylistIntoLibrary(1, 2);
		user.deletePlaylistFromLibrary(1, 2);
	}

	@Test
	public void testGetAllPlaylists() throws UserException, SQLException {
		List<Playlist> playlists = user.getAllPlaylistsForUser(1);
		for (Playlist play : playlists) {
			assertNotNull(play);
		}
	}

	@Test
	public void testAddLike() throws UserException {
		user.addLike(1, 1, 1);
		user.updateLike(1, 1, 2);
		user.deleteLike(1, 1);
	}

	@Test
	public void testCountLikes() throws UserException {
		int count = user.getCountFromLikes(1, 1);
		System.out.println(count);
	}

	@Test
	public void testAddHistory() throws UserException {
		user.addClipToHistory(1, 1);
		user.deleteClipFromHistory(1, 1);
	}

	@Test
	public void testDeleteAllHistory() throws UserException {
		user.deleteAllClipsFromHistory(1);
	}

	@Test
	public void testGetHistory() throws UserException {
		List<Clip> clips = user.getHistory(2);
		for (Clip cl : clips) {
			System.out.println(cl.getName());
			assertNotNull(cl);
		}
	}

}
