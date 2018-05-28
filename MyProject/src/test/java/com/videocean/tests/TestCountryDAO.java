package com.videocean.tests;

import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import org.junit.Test;

import com.videocean.service.dao.CountryDAO;
import com.videocean.exception.UserException;

public class TestCountryDAO {

	CountryDAO countryDAO = new CountryDAO();

	@Test
	public void testAddCountry() throws SQLException, UserException {
		countryDAO.addCountry("Portugal");// every time
	}

	@Test
	public void testGetID() throws SQLException, UserException {
		String country = countryDAO.getCountryById(1);
		assertNotNull(country);
	}

	@Test
	public void testGetName() throws SQLException, UserException {
		countryDAO.getCountryByName("Bulgaria");

	}

}
