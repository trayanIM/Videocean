package com.videocean.service.dao;

import com.videocean.exception.UserException;

public interface ILanguageDAO {

	int addLanguage(String language) throws UserException;

	String getLanguageById(int languageId) throws UserException;

	int getLanguageByName(String language) throws UserException;

}