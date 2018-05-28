package com.videocean.service.dao;

import com.videocean.service.dbconnection.AbstractDAO;
import com.videocean.exception.UserException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LanguageDAO extends AbstractDAO implements ILanguageDAO {
	private static final String SELECT_LANGUAGE_BY_ID_QUERY = "SELECT * FROM languages WHERE language_id = ?";
	private static final String ADD_LANGUAGE_QUERY = "INSERT INTO languages VALUES (null, ?)";
	private static final String SELECT_LANGUAGE_BY_NAME_QUERY = "SELECT * FROM languages WHERE language like ?";

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
				e.printStackTrace();
				throw new UserException("Can't add a language", e);
			} finally {
				CategoryDAO.closeConnection(ps, id);
			}
		} else {
			throw new UserException("Can't add a language");
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
			e.printStackTrace();
			throw new UserException("Can't find an language with ID : " + languageId, e);
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
			e.printStackTrace();
			throw new UserException("Can't find an language with this name : " + language, e);
		} finally {
			CategoryDAO.closeConnection(ps, result);
		}
	}
}
