package com.videocean.service.dao;

import com.videocean.model.User;
import com.videocean.exception.UserException;

import java.util.List;

public interface ISubscriptionFollowersDAO {

	void addSubscriptionFollower(int subscriptionId, int followerId) throws UserException;

	void deleteSubscription(int subscriptionId, int followerId) throws UserException;

	List<User> getSubscriptions(int userId) throws UserException;

	List<User> getFollowers(int userId) throws UserException;

}