package com.example.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class User implements IUser {
	private int userID;
	private String username;
	private String password;
	private String fullName;
	private String picture;
	private String backgroundPicture;
	private String country;
	private String language;
	private List<Clip> history;
	private Playlist myClips;
	private List<Playlist> playlists;
	private boolean isVerified;
	private List<User> followers;
	private List<User> subscriptions;

	public User(String username, String password, String fullName) {
		setUsername(username);
		setPassword(password);
		try {
			setFullName(fullName);
		} catch (NameFormatException e) {
			// TODO Auto-generated catch block
			e.getMessage();
			e.printStackTrace();
		}
		playlists = new ArrayList<Playlist>();
		history = new ArrayList<Clip>();
		followers = new ArrayList<User>();
		subscriptions = new ArrayList<User>();

	}

	public User(int id, String email, String fullName) {
		this.userID = id;
		setUsername(email);
		try {
			setFullName(fullName);
		} catch (NameFormatException e) {
			// TODO Auto-generated catch block
			e.getMessage();
			e.printStackTrace();
		}
		playlists = new ArrayList<Playlist>();
		history = new ArrayList<Clip>();
		followers = new ArrayList<User>();
		subscriptions = new ArrayList<User>();
	}

	public User() {

	}

	public int getUserID() {
		return userID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		if (username != null) {
			if (username.matches(
					"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*" + "@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})")) {
				this.username = username;
			}
		} else {
			try {
				throw new UsernameException("Your username is incorrect.Please try again!");
			} catch (UsernameException e) {
				e.getMessage();
			}
		}
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		if (password != null) {
			this.password = password;
		}
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) throws NameFormatException {
		if (fullName != null) {
			if (fullName.matches("[a-zA-Z]+( +[a-zA-Z]+)*")) {
				this.fullName = fullName;
			}
		} else {
			throw new NameFormatException("Your name is wrong.USE ONLY LETTERS AND SPACE!");
		}

	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) throws PictureFormatException {
		if (picture != null) {
			if (picture.endsWith(".jpg") || picture.endsWith(".jpeg") || picture.endsWith(".png")) {
				this.picture = picture;
			} else {
				throw new PictureFormatException();
			}
		}
	}

	public String getBackgroundPicture() {
		return backgroundPicture;
	}

	public void setBackgroundPicture(String backgroundPicture) throws PictureFormatException {
		if (backgroundPicture != null) {
			if (backgroundPicture.endsWith(".jpg") || backgroundPicture.endsWith(".jpeg")
					|| backgroundPicture.endsWith(".png")) {
				this.backgroundPicture = backgroundPicture;
			} else {
				throw new PictureFormatException();
			}
		}
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		if (country != null) {
			this.country = country;
		}
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		if (language != null) {
			this.language = language;
		}
	}

	public boolean isVerified() {
		return isVerified;
	}

	public void setVerified(boolean isVerified) {
		this.isVerified = isVerified;
	}

	public Playlist getMyClips() {
		return (Playlist) myClips;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pojo.IUser#addClipToMyClips(java.lang.String, java.lang.String)
	 */
	@Override
	public void addClipToMyClips(Clip clip) throws ClipException, PlaylistException {
		if (this.myClips == null) {
			this.myClips = new Playlist("myClips", this, TYPE.PUBLIC);
			this.myClips.addClipToPlaylist(clip);
		} else {
			this.myClips.addClipToPlaylist(clip);
		}
	}

	@Override
	public void removeClipFromMyClips(Clip clip) throws PlaylistException {
		this.myClips.removeClipFromPlaylist(clip);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pojo.IUser#addClipIntoPlaylist(pojo.Playlist, pojo.Clip)
	 */
	@Override
	public void addClipIntoPlaylist(Playlist playlist, Clip clip) {
		if (playlist != null) {
			for (Playlist search : this.playlists) {
				if (search.equals(playlist)) {
					search.addClipToPlaylist(clip);
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pojo.IUser#removeClipFromPlaylist(pojo.Playlist, java.lang.String)
	 */
	@Override
	public void removeClipFromPlaylist(Playlist playlist, Clip clip) throws PlaylistException {
		if (playlist != null) {
			for (Playlist search : this.playlists) {
				if (search.equals(playlist)) {
					search.removeClipFromPlaylist(clip);
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pojo.IUser#makePlaylist(java.lang.String)
	 */
	@Override
	public void makePlaylist(String name) throws PlaylistException {
		this.playlists.add(new Playlist(name, this, TYPE.PUBLIC));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pojo.IUser#addPlaylist(pojo.Playlist)
	 */
	@Override
	public void addPlaylist(Playlist playlist) {
		if (playlist != null) {
			this.playlists.add(playlist);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pojo.IUser#removePlaylist(pojo.Playlist)
	 */
	@Override
	public void removePlaylist(Playlist playlist) throws PlaylistException {
		if (playlist != null) {
			if (this.playlists.contains(playlist)) {
				this.playlists.remove(playlist);
			} else {
				throw new PlaylistException("Playlist doesn't exist");
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pojo.IUser#addFollower(pojo.User)
	 */
	@Override
	public void addFollower(User user) throws UserProblemException {
		if (user != null) {
			this.followers.add(user);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pojo.IUser#addSubscrition(pojo.User)
	 */
	@Override
	public void addSubscrition(User user) throws UserProblemException {
		if (user != null) {
			this.subscriptions.add(user);
			user.addFollower(this);

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pojo.IUser#removeFollower(pojo.IUser)
	 */
	@Override
	public void removeFollower(IUser user) throws UserProblemException {
		if (user != null) {
			if (this.followers.contains(user)) {
				this.followers.remove(user);
			} else {
				throw new UserProblemException("User doesn't exist");
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pojo.IUser#removeSubscription(pojo.IUser)
	 */
	@Override
	public void removeSubscription(IUser user) throws UserProblemException {
		if (user != null) {
			if (this.subscriptions.contains(user)) {
				this.subscriptions.remove(user);
				user.removeFollower(this);
			} else {
				throw new UserProblemException("User doesn't exist");
			}
		}
	}

	public void AddClipToHistory(Clip clip) {
		if (clip != null) {
			this.history.add(clip);
		}
	}

	public List<Clip> getClipsFromHistory() {
		return Collections.unmodifiableList(this.history);
	}

	public List<User> getUsersFromSupscription() {
		return Collections.unmodifiableList(this.subscriptions);
	}

	public List<User> getUsersFromFollowers() {
		return Collections.unmodifiableList(this.followers);
	}

	public List<Playlist> getPlaylistFromPlaylists() {
		return Collections.unmodifiableList(this.playlists);
	}

}
