package com.example.tests;

import static org.junit.Assert.*;
import java.sql.SQLException;
import java.util.List;
import org.junit.Test;

import com.example.model.AbstractDAO;
import com.example.model.Clip;
import com.example.model.DBConnection;
import com.example.model.PictureFormatException;
import com.example.model.Playlist;
import com.example.model.User;
import com.example.model.UserDAO;
import com.example.model.UserProblemException;

public class TestUserDAO extends AbstractDAO {
	UserDAO user = new UserDAO();

	@Test
	public void testConnection() {
		java.sql.Connection con = DBConnection.getInstance().getConnection();
		assertNotNull(con);
	}

	@Test
	public void testAddUser() throws UserProblemException {
		int forDelete;
		forDelete = user.addUser(new User("mrayan@abv.bg", "123456789", "trayan muchev"));
		user.removeUser(forDelete);
	}

	@Test
	public void testUpdateUser() throws UserProblemException, PictureFormatException {
		User us = new User(1, "tdra@abv.bg", "stoina");
		us.setPassword("1232445436");
		us.setLanguage("ENG");
		us.setBackgroundPicture("neshto.jpg");
		us.setPicture("picture.jpg");
		user.updateUser(us);
	}

	@Test
	public void testGetUserById() throws UserProblemException, SQLException {
		User us = user.getUserById(1);
		assertNotNull(us);
	}

	@Test
	public void testEmailAndName() throws UserProblemException {
		User user1 = user.getUserByEmailAndPass("tdra@abv.bg", "1232445436");
		assertNotNull(user1);
	}

	@Test
	public void testFullName() throws UserProblemException {
		List<User> users = user.getUsersByName("stoina");
		for (User u : users) {
			assertNotNull(u);
		}
	}

	@Test
	public void testAllUsers() throws UserProblemException {
		List<User> users = user.getAllUsers();
		for (User u : users) {
			assertNotNull(u);
		}
	}

	@Test
	public void testAddPlaylist() throws UserProblemException {
		user.addPlaylistIntoLibrary(1, 2);
		user.deletePlaylistFromLibrary(1, 2);
	}

	@Test
	public void testGetAllPlaylists() throws UserProblemException, SQLException {
		List<Playlist> playlists = user.getAllPlaylistsForUser(1);
		for (Playlist play : playlists) {
			assertNotNull(play);
		}
	}

	@Test
	public void testAddLike() throws UserProblemException {
		user.addLike(1, 1, 1);
		user.updateLike(1, 1, 2);
		user.deleteLike(1, 1);
	}

	@Test
	public void testCountLikes() throws UserProblemException {
		int count = user.getCountFromLikes(1, 1);
		System.out.println(count);
	}

	@Test
	public void testAddHistory() throws UserProblemException {
		user.addClipToHistory(1, 1);
		user.deleteClipFromHistory(1, 1);
	}

	@Test
	public void testDeleteAllHistory() throws UserProblemException {
		user.deleteAllClipsFromHistory(1);
	}

	@Test
	public void testGetHistory() throws UserProblemException {
		List<Clip> clips = user.getHistory(2);
		for (Clip cl : clips) {
			System.out.println(cl.getName());
			assertNotNull(cl);
		}
	}

}
