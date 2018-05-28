package com.videocean.service.dao;

import com.videocean.model.Category;
import com.videocean.model.Clip;
import com.videocean.exception.ClipException;
import com.videocean.exception.UserException;

import java.util.List;

public interface IClipDAO {

	int addClip(Clip clip) throws ClipException;

	void removeClip(int clipID) throws ClipException;

	Clip getClipByID(int clipID) throws ClipException;

	List<Clip> getAllClips() throws ClipException;

	void updateClip(Clip clip) throws UserException, ClipException;

	List<Clip> getAllClipsByCategory(Category category) throws ClipException;

}