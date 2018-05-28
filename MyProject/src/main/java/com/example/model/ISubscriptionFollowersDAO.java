package com.example.model;

import java.util.List;

public interface ISubscriptionFollowersDAO {

	void addSubscriptionFollower(int subscriptionId, int followerId) throws UserProblemException;

	void deleteSubscription(int subscriptionId, int followerId) throws UserProblemException;

	List<User> getSubscriptions(int userId) throws UserProblemException;

	List<User> getFollowers(int userId) throws UserProblemException;

}