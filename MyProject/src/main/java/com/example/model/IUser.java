package com.example.model;

import java.util.List;

public interface IUser {



	void addClipIntoPlaylist(Playlist playlist, Clip clip);

	void removeClipFromPlaylist(Playlist playlist, Clip clip) throws PlaylistException;

	void makePlaylist(String name) throws PlaylistException;

	void addPlaylist(Playlist playlist);

	void removePlaylist(Playlist playlist) throws PlaylistException;

	void addFollower(User user) throws UserProblemException;

	void addSubscrition(User user) throws UserProblemException;

	void removeFollower(IUser user) throws UserProblemException;

	void removeSubscription(IUser user) throws UserProblemException;

	void removeClipFromMyClips(Clip clip) throws PlaylistException;
	
	int getUserID();

	public void AddClipToHistory(Clip clip);
	
	public List<Clip> getClipsFromHistory();
	
	public List<User> getUsersFromSupscription();
	
	public List<User> getUsersFromFollowers();
	
	public List<Playlist> getPlaylistFromPlaylists();

	void addClipToMyClips(Clip S) throws ClipException, PlaylistException;
}