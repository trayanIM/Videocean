package com.example.model;

import java.util.List;

public interface IClipDAO {

	int addClip(Clip clip) throws ClipException;

	void removeClip(int clipID) throws ClipException;

	Clip getClipByID(int clipID) throws ClipException;

	List<Clip> getAllClips() throws ClipException;

	void updateClip(Clip clip) throws UserProblemException, ClipException;

	List<Clip> getAllClipsByCategory(Category category) throws ClipException;

}