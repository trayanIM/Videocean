package com.videocean.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.videocean.service.dao.LanguageDAO;
import com.videocean.exception.UserException;

public class TestLanguageDAO {

	private static final String LANGUAGE_NAME = "UZB";
	private LanguageDAO languageDAO = new LanguageDAO();
	private int languageId;

	@Before
	public void setUp() throws UserException {
		languageId = languageDAO.addLanguage(LANGUAGE_NAME);
	}

	@After
	public void tearDown() throws UserException {
		languageDAO.removeLanguage(languageId);
	}

	@Test
	public void testGetById() throws SQLException, UserException {
		String language = languageDAO.getLanguageById(languageId);
		assertNotNull(language);
		assertEquals(LANGUAGE_NAME, language);
	}

	@Test
	public void testGetByName() throws SQLException, UserException {
		int languageId = languageDAO.getLanguageByName(LANGUAGE_NAME);
		assertEquals(this.languageId, languageId);
	}

}
