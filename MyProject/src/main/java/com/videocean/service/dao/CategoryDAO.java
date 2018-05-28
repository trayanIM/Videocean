package com.videocean.service.dao;

import com.videocean.service.dbconnection.AbstractDAO;
import com.videocean.model.Category;
import com.videocean.exception.CategoryException;
import com.videocean.exception.UserException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class CategoryDAO extends AbstractDAO implements ICategoryDAO {

	private Logger logger = Logger.getLogger(CategoryDAO.class.getName());

	private static final String ADD_CATEGORY = "INSERT INTO category VALUES(null,?);";
	private static final String SELECT_ALL_CATEGORIES = "SELECT * FROM category;";
	private static final String SELECT_CATEGORY_BY_ID = "SELECT * FROM category WHERE category_id= ? ;";

	public int addCategory(Category category) throws UserException {
		PreparedStatement ps = null;
		if (category != null) {
			try {
				ps = getCon().prepareStatement(ADD_CATEGORY, PreparedStatement.RETURN_GENERATED_KEYS);
				ps.setString(1, category.getName());
				ps.executeUpdate();
				ResultSet id = ps.getGeneratedKeys();
				id.next();

				return id.getInt(1);
			} catch (SQLException e) {
				String errorMessage = "Category has not been created!";
				logger.info(errorMessage);
				throw new UserException(errorMessage, e);
			} finally {
				closeConnection(ps);
			}
		}
		throw new UserException("Category has not been created!");

	}

	@Override
	public Category getCategoryByID(int categoryID) throws CategoryException {
		PreparedStatement ps = null;
		try {
			ps = getCon().prepareStatement(SELECT_CATEGORY_BY_ID);
			ps.setInt(1, categoryID);
			ResultSet rs = ps.executeQuery();
			rs.next();
			int id = rs.getInt(1);
			String name = rs.getString(2);

			return new Category(id, name);
		} catch (SQLException e) {
			String errorMessage = "Can't find a category with ID : " + categoryID;
			logger.info(errorMessage);
			throw new CategoryException(errorMessage, e);
		} finally {
			closeConnection(ps);
		}
	}

	@Override
	public List<Category> getAllCategories() throws CategoryException {
		List<Category> categoriesList = new ArrayList<Category>();
		Statement statement = null;
		ResultSet rs = null;
		try {
			statement = getCon().createStatement();
			rs = statement.executeQuery(SELECT_ALL_CATEGORIES);

			while (rs.next()) {
				Category category = new Category(rs.getInt(1), rs.getString(2));
				categoriesList.add(category);
			}
			return categoriesList;
		} catch (SQLException e) {
			String errorMessage = "No have been categories found!";
			logger.info(errorMessage);
			throw new CategoryException(errorMessage, e);
		} finally {
			closeConnection(statement, rs);
		}

	}



}
