package com.example.model;

public interface ILanguageDAO {

	int addLanguage(String language) throws UserProblemException;

	String getLanguageById(int languageId) throws UserProblemException;

	int getLanguageByName(String language) throws UserProblemException;

}