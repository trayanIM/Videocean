package com.videocean.service.dao;

import com.videocean.service.dbconnection.AbstractDAO;
import com.videocean.model.User;
import com.videocean.exception.UserException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class SubscriptionFollowerDAO extends AbstractDAO implements ISubscriptionFollowersDAO {

	private Logger logger = Logger.getLogger(SubscriptionFollowerDAO.class.getName());

	private static final String ADD_FOLLOWER_QUERY = "INSERT INTO followers VALUES (?, ?)";
	private static final String DELETE_FOLLOWER_QUERY = "DELETE FROM followers WHERE user_id= ? and follower_id=?";
	private static final String SELECT_SUBSCRIPTIONS_QUERY = "SELECT follower_id from followers where user_id=?";
	private static final String SELECT_FOLLWERS_QUERY = "SELECT user_id from followers where follower_id=?";

	/*
	 * (non-Javadoc)
	 * 
	 * @see DAO.ISubscriptionFollowersDAO#addSubscriptionFollower(int, int)
	 */
	@Override
	public void addSubscriptionFollower(int subscriptionId, int followerId) throws UserException {
		if (subscriptionId > 0 && followerId > 0) {
			if (subscriptionId != followerId) {
				PreparedStatement ps = null;
				try {
					ps = getCon().prepareStatement(ADD_FOLLOWER_QUERY);
					ps.setInt(1, subscriptionId);
					ps.setInt(2, followerId);

					ps.executeUpdate();
				} catch (SQLException e) {
					String errorMessage = "There is no compozition with this subscription and this follower!";
					logger.info(errorMessage);
					throw new UserException(errorMessage, e);
				} finally {
					closeConnection(ps);
				}

			}
		}
	}

	@Override
	public void deleteSubscription(int subscriptionId, int followerId) throws UserException {
		PreparedStatement ps = null;
		try {
			ps = getCon().prepareStatement(DELETE_FOLLOWER_QUERY);
			ps.setInt(1, subscriptionId);
			ps.setInt(2, followerId);

			ps.executeUpdate();
		} catch (SQLException e) {
			String errorMessage = "There is no compozition with this subscription and this follower!";
			logger.info(errorMessage);
			throw new UserException(errorMessage, e);
		} finally {
			closeConnection(ps);
		}
	}

	@Override
	public List<User> getSubscriptions(int userId) throws UserException {
		return getFollowersOrSubscriptions(userId, SELECT_SUBSCRIPTIONS_QUERY);
	}

	@Override
	public List<User> getFollowers(int userId) throws UserException {
		return getFollowersOrSubscriptions(userId, SELECT_FOLLWERS_QUERY);
	}

	private List<User> getFollowersOrSubscriptions(int userId, String query) throws UserException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<User> subscriptions = new ArrayList<User>();
		try {
			ps = getCon().prepareStatement(query);
			ps.setInt(1, userId);

			UserDAO user = new UserDAO();

			rs = ps.executeQuery();
			while (rs.next()) {
				subscriptions.add(user.getUserById(rs.getInt(1)));
			}
		} catch (SQLException | UserException e) {
			String errorMessage = "Can't load the subscriptions or followers!";
			logger.info(errorMessage);
			throw new UserException(errorMessage, e);
		} finally {
			closeConnection(ps);
		}
		return subscriptions;

	}

	public int getState(int userId, int follower) throws UserException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		int state = 0;
		try {
			ps = getCon().prepareStatement("Select follower_id from followers where user_id=? and follower_id=?;");
			ps.setInt(1, userId);
			ps.setInt(2, follower);

			rs = ps.executeQuery();
			if (rs.next()) {
				state = rs.getInt(1);
			}
		} catch (SQLException e) {
			String errorMessage = "Can't load the subscriptions or followers!";
			logger.info(errorMessage);
			throw new UserException(errorMessage, e);
		} finally {
			closeConnection(ps);
		}
		return state;

	}

}
