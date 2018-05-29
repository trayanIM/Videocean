package com.videocean.service.dao;

import com.videocean.exception.UserException;
import com.videocean.service.dbconnection.AbstractDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class CountryDAO extends AbstractDAO implements ICountryDAO {

    private Logger logger = Logger.getLogger(CountryDAO.class.getName());

    private static final String SELECT_COUTRY_BY_ID_QUERY = "SELECT * FROM countries WHERE country_id = ?";
    private static final String ADD_COUNTRY_QUERY = "INSERT INTO countries VALUES (null, ?)";
    private static final String SELECT_COUTRY_BY_NAME_QUERY = "SELECT * FROM countries WHERE country_name like ?";
    private static final String DELETE_FROM_COUNTRY_BY_ID = "DELETE FROM countries WHERE country_id = ?;";

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
                String errorMessage = "Unable to add country!";
                logger.info(errorMessage);
                throw new UserException(errorMessage, e);
            } finally {
                CategoryDAO.closeConnection(ps, id);
            }
        }
        throw new UserException("Unable to add country!");
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
            String errorMessage = "Can't find a country with ID : " + countryId;
            logger.info(errorMessage);
            throw new UserException(errorMessage, e);
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
            String errorMessage = "Can't find an country with this name : " + countryName;
            logger.info(errorMessage);
            throw new UserException(errorMessage, e);
        } finally {
            CategoryDAO.closeConnection(ps, result);
        }
    }

    @Override
    public void removeCountry(int countryId) throws UserException {
        PreparedStatement ps = null;
        try {
            ps = getCon().prepareStatement(DELETE_FROM_COUNTRY_BY_ID);
            ps.setInt(1, countryId);
            ps.executeUpdate();
        } catch (Exception e) {
            throw new UserException("A problem appeared while deleting country with id " + countryId , e);
        } finally {
            closeConnection(ps);
        }
    }

}
