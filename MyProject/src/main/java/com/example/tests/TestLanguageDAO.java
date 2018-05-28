package com.example.tests;

import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;

import org.junit.Test;

import com.example.model.LanguageDAO;
import com.example.model.UserProblemException;

public class TestLanguageDAO {

	LanguageDAO languageDAO = new LanguageDAO();

	@Test
	public void testAddLanguage() throws SQLException, UserProblemException {
		languageDAO.addLanguage("GBR");// different every time
	}

	@Test
	public void testGetID() throws SQLException, UserProblemException {
		String language = languageDAO.getLanguageById(1);
		assertNotNull(language);
	}

	@Test
	public void testGetName() throws SQLException, UserProblemException {
		languageDAO.getLanguageByName("ENG");
	}

}
