package com.videocean.service.dao;

import com.videocean.exception.UserException;

public interface ICountryDAO {

	int addCountry(String country) throws UserException;

	String getCountryById(int countryId) throws UserException;

	int getCountryByName(String countryName) throws UserException;

}