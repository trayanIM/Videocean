package com.example.model;

public class Statistics {
	private int dailyViews;
	private double timeWatched;
	private int subscriptionsDriven;
	private int shares;

	public int getDailyViews() {
		return dailyViews;
	}

	public void setDailyViews(int dailyViews) {
		if (dailyViews > 0)
			this.dailyViews = dailyViews;
	}

	public double getTimeWatched() {
		return timeWatched;
	}

	public void setTimeWatched(double timeWatched) {
		if (timeWatched > 0)
			this.timeWatched = timeWatched;
	}

	public int getSubscriptionsDriven() {
		return subscriptionsDriven;
	}

	public void setSubscriptionsDriven(int subscriptionsDriven) {
		if (subscriptionsDriven > 0)
			this.subscriptionsDriven = subscriptionsDriven;
	}

	public int getShares() {
		return shares;
	}

	public void setShares(int shares) {
		if (shares > 0)
			this.shares = shares;
	}

}
