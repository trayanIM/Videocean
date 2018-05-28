package com.videocean.service.dao;

import com.videocean.exception.CategoryException;
import com.videocean.model.Category;

import java.util.List;

public interface ICategoryDAO {

    Category getCategoryByID(int categoryID) throws CategoryException;

    List<Category> getAllCategories() throws CategoryException;

}