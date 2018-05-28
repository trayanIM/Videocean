package com.example.model;

public class Category {

	private int categoryID;
	private String name;

	public Category(int categoryID, String name) {
		this.categoryID = categoryID;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCategoryID() {
		return categoryID;
	}

}
