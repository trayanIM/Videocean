package com.videocean.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;

import com.videocean.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.videocean.service.dao.CountryDAO;
import com.videocean.exception.UserException;

public class TestCountryDAO {

	private static final String COUNTRY_NAME = "Uzbekistan";
	private CountryDAO countryDAO = new CountryDAO();
	private int countryId;

	@Before
	public void setUp() throws UserException {
		countryId = countryDAO.addCountry(COUNTRY_NAME);
	}

	@After
	public void tearDown() throws UserException {
		countryDAO.removeCountry(countryId);
	}

	@Test
	public void testGetById() throws SQLException, UserException {
		String country = countryDAO.getCountryById(countryId);
		assertNotNull(country);
		assertEquals(COUNTRY_NAME, country);
	}

	@Test
	public void testGetByName() throws SQLException, UserException {
		int countryId = countryDAO.getCountryByName(COUNTRY_NAME);
		assertEquals(this.countryId, countryId);
	}

}
