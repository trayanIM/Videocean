package com.videocean.tests;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import com.videocean.model.Category;
import com.videocean.service.dao.CategoryDAO;
import com.videocean.exception.CategoryException;

public class TestCategoryDAO {
	CategoryDAO categoryDAO = new CategoryDAO();

	@Test
	public void testGetID() throws CategoryException, SQLException {

		Category category = categoryDAO.getCategoryByID(1);
		assertNotNull(category);
	}

	@Test
	public void testGetAll() throws CategoryException, SQLException {

		List<Category> categories = categoryDAO.getAllCategories();
		for (Category c : categories) {
			assertNotNull(c);
		}
	}

}
