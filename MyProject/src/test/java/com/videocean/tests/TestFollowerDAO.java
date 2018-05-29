package com.videocean.tests;

import java.sql.SQLException;
import java.util.List;

import com.videocean.service.dao.UserDAO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.videocean.service.dao.SubscriptionFollowerDAO;
import com.videocean.model.User;
import com.videocean.exception.UserException;

import static org.junit.Assert.*;

public class TestFollowerDAO {

	private SubscriptionFollowerDAO subDAO = new SubscriptionFollowerDAO();
	private UserDAO userDAO = new UserDAO();
	private int subscriberId;
	private int followerId;

	@Before
	public void setUp() throws UserException {
		User subscriber = new User("trayanI@gmail.com", "1234567", "Muchev");
		User follower = new User("trayanMI@gmail.com", "1234567", "Muchev");
		subscriberId = userDAO.addUser(subscriber);
		followerId = userDAO.addUser(follower);
	}

	@After
	public void tearDown() throws UserException {
		userDAO.removeUser(subscriberId);
		userDAO.removeUser(followerId);
	}

	@Test
	public void testGetAllSubs() throws SQLException, UserException {
		subDAO.addSubscriptionFollower(subscriberId, followerId);
		List<User> subscriptions = subDAO.getSubscriptions(subscriberId);
		assertNotNull(subscriptions);
		assertEquals(1, subscriptions.size());
		assertEquals(followerId, subscriptions.get(0).getUserID());
		subDAO.deleteSubscription(subscriberId, followerId);
	}

	@Test
	public void testGetAllFollower() throws SQLException, UserException {
		subDAO.addSubscriptionFollower(subscriberId, followerId);
		List<User> followers = subDAO.getFollowers(followerId);
		assertNotNull(followers);
		assertEquals(1, followers.size());
		assertEquals(subscriberId, followers.get(0).getUserID());
		subDAO.deleteSubscription(subscriberId, followerId);
	}

}
