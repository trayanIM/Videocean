package com.example.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.model.User;

public class Admin extends User {
	private Map<Integer, User> users;

	public Admin(String username, String password, String fullName) {
		super(username, password, fullName);
		users = new HashMap<Integer, User>();
	}

	public Admin(int id, String email, String fullName) {
		super(id, email, fullName);
		users = new HashMap<Integer, User>();
	}

	public void addUser(User user) {
		users.put(user.getUserID(), user);
	}

	public void removeUser(int userId) {
		users.remove(userId);
	}

	public List<User> getUsers() {
		List<User> wantedUsers = new ArrayList<User>();
		User forAdding;
		for (int key : users.keySet()) {
			forAdding = users.get(key);
			wantedUsers.add(forAdding);
		}
		return wantedUsers;
	}

}
