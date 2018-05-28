package com.videocean.tests;

import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;

import org.junit.Test;

import com.videocean.service.dao.LanguageDAO;
import com.videocean.exception.UserException;

public class TestLanguageDAO {

	LanguageDAO languageDAO = new LanguageDAO();

	@Test
	public void testAddLanguage() throws SQLException, UserException {
		languageDAO.addLanguage("GBR");// different every time
	}

	@Test
	public void testGetID() throws SQLException, UserException {
		String language = languageDAO.getLanguageById(1);
		assertNotNull(language);
	}

	@Test
	public void testGetName() throws SQLException, UserException {
		languageDAO.getLanguageByName("ENG");
	}

}
