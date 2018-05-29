package com.videocean.service.dao;

import com.videocean.service.dbconnection.AbstractDAO;
import com.videocean.exception.UserException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class LanguageDAO extends AbstractDAO implements ILanguageDAO {

	private Logger logger = Logger.getLogger(LanguageDAO.class.getName());

	private static final String SELECT_LANGUAGE_BY_ID_QUERY = "SELECT * FROM languages WHERE language_id = ?";
	private static final String ADD_LANGUAGE_QUERY = "INSERT INTO languages VALUES (null, ?)";
	private static final String SELECT_LANGUAGE_BY_NAME_QUERY = "SELECT * FROM languages WHERE language like ?";
	private static final String DELETE_FROM_LANGUAGE_BY_ID = "DELETE FROM languages WHERE language_id = ?;";

	/*
	 * (non-Javadoc)
	 * 
	 * @see DAO.ILanguage#addLanguage(java.lang.String)
	 */
	@Override
	public int addLanguage(String language) throws UserException {
		PreparedStatement ps = null;
		ResultSet id = null;
		if (language != null) {
			try {
				ps = getCon().prepareStatement(ADD_LANGUAGE_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
				ps.setString(1, language);
				ps.executeUpdate();

				id = ps.getGeneratedKeys();
				id.next();
				return id.getInt(1);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				String errorMessage = "Unable to add language!";
				logger.info(errorMessage);
				throw new UserException(errorMessage, e);
			} finally {
				CategoryDAO.closeConnection(ps, id);
			}
		} else {
			throw new UserException("Unable to add language!");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see DAO.ILanguage#getLanguageById(int)
	 */
	@Override
	public String getLanguageById(int languageId) throws UserException {
		PreparedStatement ps = null;
		ResultSet result = null;
		try {
			ps = getCon().prepareStatement(SELECT_LANGUAGE_BY_ID_QUERY);
			ps.setInt(1, languageId);
			result = ps.executeQuery();
			result.next();
			String languageName = result.getString(2);

			return languageName;
		} catch (SQLException e) {
			String errorMessage = "Can't find a language with ID : " + languageId;
			logger.info(errorMessage);
			throw new UserException(errorMessage, e);
		} finally {
			CategoryDAO.closeConnection(ps, result);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see DAO.ILanguage#getLanguageByName(java.lang.String)
	 */
	@Override
	public int getLanguageByName(String language) throws UserException {
		PreparedStatement ps = null;
		ResultSet result = null;
		try {
			ps = getCon().prepareStatement(SELECT_LANGUAGE_BY_NAME_QUERY);
			ps.setString(1, language);
			result = ps.executeQuery();
			result.next();
			int languageId = result.getInt(1);

			return languageId;
		} catch (SQLException e) {
			String errorMessage = "Can't find an language with this name : " + language;
			logger.info(errorMessage);
			throw new UserException(errorMessage, e);
		} finally {
			CategoryDAO.closeConnection(ps, result);
		}
	}

	@Override
	public void removeLanguage(int languageId) throws UserException {
		PreparedStatement ps = null;
		try {
			ps = getCon().prepareStatement(DELETE_FROM_LANGUAGE_BY_ID);
			ps.setInt(1, languageId);
			ps.executeUpdate();
		} catch (Exception e) {
			throw new UserException("A problem appeared while deleting language with id " + languageId , e);
		} finally {
			closeConnection(ps);
		}
	}
}
