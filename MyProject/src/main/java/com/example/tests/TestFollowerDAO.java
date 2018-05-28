package com.example.tests;

import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import com.example.model.SubscriptionFollowerDAO;
import com.example.model.User;
import com.example.model.UserProblemException;

public class TestFollowerDAO {

	SubscriptionFollowerDAO subDAO = new SubscriptionFollowerDAO();

	@Test
	public void testAddRemoveFollower() throws UserProblemException {
		subDAO.addSubscriptionFollower(1, 2);
		subDAO.deleteSubscription(1, 2);
	}

	@Test
	public void testGetAllSubs() throws SQLException, UserProblemException {
		List<User> sub = subDAO.getSubscriptions(2);
		for (User us : sub) {
			System.out.println(us.getFullName());
			assertNotNull(us);
		}
	}

	@Test
	public void testGetAllFollower() throws SQLException, UserProblemException {
		List<User> sub = subDAO.getFollowers(1);
		for (User us : sub) {
			System.out.println(us.getFullName());
			assertNotNull(us);
		}
	}

}
