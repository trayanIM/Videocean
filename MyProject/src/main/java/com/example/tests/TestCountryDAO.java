package com.example.tests;

import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import org.junit.Test;

import com.example.model.CountryDAO;
import com.example.model.UserProblemException;

public class TestCountryDAO {

	CountryDAO countryDAO = new CountryDAO();

	@Test
	public void testAddCountry() throws SQLException, UserProblemException {
		countryDAO.addCountry("Portugal");// every time
	}

	@Test
	public void testGetID() throws SQLException, UserProblemException {
		String country = countryDAO.getCountryById(1);
		assertNotNull(country);
	}

	@Test
	public void testGetName() throws SQLException, UserProblemException {
		countryDAO.getCountryByName("Bulgaria");

	}

}
