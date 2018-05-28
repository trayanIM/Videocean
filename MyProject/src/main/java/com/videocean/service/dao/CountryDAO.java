package com.videocean.service.dao;

import com.videocean.service.dbconnection.AbstractDAO;
import com.videocean.exception.UserException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CountryDAO extends AbstractDAO implements ICountryDAO {
	private static final String SELECT_COUTRY_BY_ID_QUERY = "SELECT * FROM countries WHERE country_id = ?";
	private static final String ADD_COUNTRY_QUERY = "INSERT INTO countries VALUES (null, ?)";
	private static final String SELECT_COUTRY_BY_NAME_QUERY = "SELECT * FROM countries WHERE country_name like ?";

	/*
	 * (non-Javadoc)
	 * 
	 * @see DAO.ICountry#addCountry(java.lang.String)
	 */
	@Override
	public int addCountry(String country) throws UserException {
		PreparedStatement ps = null;
		ResultSet id = null;
		if (country != null) {
			try {
				ps = getCon().prepareStatement(ADD_COUNTRY_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
				ps.setString(1, country);
				ps.executeUpdate();

				id = ps.getGeneratedKeys();
				id.next();
				return id.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new UserException("Can't add a country", e);
			} finally {
				CategoryDAO.closeConnection(ps, id);
			}
		}
		throw new UserException("Can't add category");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see DAO.ICountry#getCountryById(int)
	 */
	@Override
	public String getCountryById(int countryId) throws UserException {
		PreparedStatement ps = null;
		ResultSet result = null;
		try {
			ps = getCon().prepareStatement(SELECT_COUTRY_BY_ID_QUERY);
			ps.setInt(1, countryId);
			result = ps.executeQuery();
			result.next();
			String country_name = result.getString(2);

			return country_name;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserException("Can't find an country name with ID : " + countryId, e);
		} finally {
			CategoryDAO.closeConnection(ps, result);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see DAO.ICountry#getCountryByName(java.lang.String)
	 */
	@Override
	public int getCountryByName(String countryName) throws UserException {
		PreparedStatement ps = null;
		ResultSet result = null;
		try {
			ps = getCon().prepareStatement(SELECT_COUTRY_BY_NAME_QUERY);
			ps.setString(1, countryName);
			result = ps.executeQuery();
			result.next();
			int countryId = result.getInt(1);

			return countryId;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserException("Can't find an country with this name : " + countryName, e);
		} finally {
			CategoryDAO.closeConnection(ps, result);
		}
	}

}
