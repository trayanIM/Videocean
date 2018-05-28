package com.example.model;

import java.util.List;

public interface ICategoryDAO {

	Category getCategoryByID(int categoryID) throws CategoryException;

	List<Category> getAllCategories() throws CategoryException;

}