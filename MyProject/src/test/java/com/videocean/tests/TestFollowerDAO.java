package com.videocean.tests;

import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import com.videocean.service.dao.SubscriptionFollowerDAO;
import com.videocean.model.User;
import com.videocean.exception.UserException;

public class TestFollowerDAO {

	SubscriptionFollowerDAO subDAO = new SubscriptionFollowerDAO();

	@Test
	public void testAddRemoveFollower() throws UserException {
		subDAO.addSubscriptionFollower(1, 2);
		subDAO.deleteSubscription(1, 2);
	}

	@Test
	public void testGetAllSubs() throws SQLException, UserException {
		List<User> sub = subDAO.getSubscriptions(2);
		for (User us : sub) {
			System.out.println(us.getFullName());
			assertNotNull(us);
		}
	}

	@Test
	public void testGetAllFollower() throws SQLException, UserException {
		List<User> sub = subDAO.getFollowers(1);
		for (User us : sub) {
			System.out.println(us.getFullName());
			assertNotNull(us);
		}
	}

}
