package com.example.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends AbstractDAO implements IUserDAO {
	private static final String SELECT_ALL_USERS = "SELECT * from users;";
	private static final String GET_HISTORY = "SELECT clip_id FROM history WHERE user_id =? ;";
	private static final String DELETE_ALL_CLIPS_OF_USER = "DELETE FROM history WHERE user_id=?;";
	private static final String DELETE_CLIP_FROM_HISTORY = "DELETE FROM history WHERE user_id= ? and clip_id=? ;";
	private static final String ADD_TO_HISTORY = "INSERT INTO history VALUES(null,?,?);";
	private static final String SELECT_COUNT_LIKES_OR_DISLIKES = "SELECT count(user_id) from likes where clip_id=? and preference=?;";
	private static final String DELETE_FROM_LIKES = "DELETE from likes where user_id=? and clip_id=?;";
	private static final String UPDATE_LIKES = "UPDATE likes SET preference=? where user_id=? and clip_id=?";
	private static final String ADD_INTO_LIKES = "INSERT INTO likes VALUES(?,?,?);";
	private static final String SELECT_PLAYLISTS_FROM_LIBRARY = "SELECT playlist_id FROM library where user_id=?;";
	private static final String DELETE_FROM_LIBRARY = "DELETE from library where user_id=? and playlist_id=?;";
	private static final String ADD_INTO_LIBRARY = "INSERT INTO library VALUES(?,?);";
	private static final String SELECT_FROM_USERS_WHERE_FULL_NAME_LIKE = "SELECT * from users where full_name like ?;";
	private static final String SELECT_USER_BY_PASS_AND_EMAIL = "SELECT * from users where email like ? and password like md5(?);";
	private static final String SELECT_FROM_USERS_BY_USER_ID = "SELECT * from users where user_id=?;";
	private static final String DELETE_FROM_USERS_BY_ID = "DELETE FROM users WHERE user_id = ?;";
	private static final String ADD_USER_QUERY = "INSERT INTO users (email,password,full_name) VALUES (?,md5(?),?);";
	private static final String UPDATE_USER_QUERY = "UPDATE users SET email = ?, password =md5(?), full_name = ? , picture=?,country_id=?,language_id=?,background_picture=? WHERE user_id = ?;";

	/*
	 * (non-Javadoc)
	 * 
	 * @see DAO.IUser#addUser(classes.User)
	 */
	@Override
	public int addUser(User user) throws UserProblemException {
		if (user != null) {
			PreparedStatement ps = null;
			ResultSet id = null;
			try {
				ps = getCon().prepareStatement(ADD_USER_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
				ps.setString(1, user.getUsername());
				ps.setString(2, user.getPassword());
				ps.setString(3, user.getFullName());

				ps.executeUpdate();

				id = ps.getGeneratedKeys();
				id.next();
				return id.getInt(1);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new UserProblemException("invalid user");
			} finally {
				try {
					if (ps != null)
						ps.close();
					if (id != null)
						id.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} else {
			throw new UserProblemException("Invalid user");
		}
	}

	public int countTheLikesOfThisUserClips(User user) throws UserProblemException {
		if (user != null) {
			try {
				PreparedStatement ps = getCon().prepareStatement("SELECT SUM(views) from clips where owner_id=? ");
				ps.setInt(1, user.getUserID());

				ResultSet rs = ps.executeQuery();
				rs.next();
				int count = rs.getInt(1);
				return count;
			} catch (SQLException e) {
				e.printStackTrace();
				throw new UserProblemException("This owner doesnt have views");
			}
		} else {
			throw new UserProblemException("Wrong Id");
		}
	}

	public int countTheViewsOfThisUserClips(User user) throws UserProblemException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		if (user != null) {
			try {
				ps = getCon().prepareStatement("SELECT SUM(views) from clips where owner_id=? ");
				ps.setInt(1, user.getUserID());

				rs = ps.executeQuery();
				rs.next();
				int count = rs.getInt(1);
				return count;
			} catch (SQLException e) {
				e.printStackTrace();
				throw new UserProblemException("This owner doesnt have views");
			} finally {
				try {
					if (ps != null)
						ps.close();
					if (rs != null)
						rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} else {
			throw new UserProblemException("Wrong Id");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see DAO.IUser#updateUser(classes.User)
	 */
	@Override
	public void updateUser(User user) throws UserProblemException {
		if (user != null) {
			PreparedStatement ps = null;

			try {
				ps = getCon().prepareStatement(UPDATE_USER_QUERY);

				ICountryDAO country = (ICountryDAO) new CountryDAO();
				ILanguageDAO language = new LanguageDAO();

				ps.setString(1, user.getUsername());
				ps.setString(2, user.getPassword());
				ps.setString(3, user.getFullName());
				ps.setString(4, user.getPicture());

				if (user.getCountry() != null) {
					ps.setInt(5, country.getCountryByName(user.getCountry()));
				} else {
					ps.setNull(5, java.sql.Types.INTEGER);
				}
				ps.setInt(6, language.getLanguageByName(user.getLanguage()));
				ps.setString(7, user.getBackgroundPicture());
				ps.setInt(8, user.getUserID());

				ps.executeUpdate();
			} catch (SQLException | UserProblemException e) {
				e.printStackTrace();
				throw new UserProblemException("Update failed");
			} finally {
				try {
					if (ps != null)
						ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see DAO.IUser#removeUser(int)
	 */
	@Override
	public void removeUser(int userID) throws UserProblemException {
		PreparedStatement ps = null;
		try {
			ps = getCon().prepareStatement(DELETE_FROM_USERS_BY_ID);
			ps.setInt(1, userID);
			ps.executeUpdate();
		} catch (Exception e) {
			throw new UserProblemException("The user cannot be deleted right now. Thank you.", e);
		} finally {
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see DAO.IUser#getUserById(int)
	 */
	@Override
	public User getUserById(int userId) throws UserProblemException {
		if (userId > 0) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = getCon().prepareStatement(SELECT_FROM_USERS_BY_USER_ID);
				ps.setInt(1, userId);

				rs = ps.executeQuery();
				rs.next();
				return getWantedUser(rs);
			} catch (SQLException | PictureFormatException | UserProblemException e) {
				e.printStackTrace();
				throw new UserProblemException("Can't return user.Please try again", e);
			} finally {
				try {
					if (ps != null)
						ps.close();
					if (rs != null)
						rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} else {
			throw new UserProblemException("Wrong Id");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see DAO.IUser#getUserByEmailAndPass(java.lang.String, java.lang.String)
	 */
	@Override
	public User getUserByEmailAndPass(String email, String password) throws UserProblemException {
		if (email != null && password != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = getCon().prepareStatement(SELECT_USER_BY_PASS_AND_EMAIL);
				ps.setString(1, email);
				ps.setString(2, password);

				rs = ps.executeQuery();
				boolean exist = rs.next();
				if (exist) {

					return getWantedUser(rs);
				} else {
					throw new UserProblemException("Wrong email or password");
				}
			} catch (SQLException | PictureFormatException | UserProblemException e) {
				e.printStackTrace();
				throw new UserProblemException("Wrong email or password", e);
			} finally {
				try {
					if (ps != null)
						ps.close();
					if (rs != null)
						rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} else {
			throw new UserProblemException("Wrong email or password");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see DAO.IUser#getUsersByName(java.lang.String)
	 */
	@Override
	public List<User> getUsersByName(String name) throws UserProblemException {
		if (name != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			List<User> wantedUsers = new ArrayList<User>();
			try {
				ps = getCon().prepareStatement(SELECT_FROM_USERS_WHERE_FULL_NAME_LIKE);
				ps.setString(1, "%" + name + "%");

				rs = ps.executeQuery();
				while (rs.next()) {
					wantedUsers.add(getWantedUser(rs));
				}
				return wantedUsers;
			} catch (SQLException | PictureFormatException | UserProblemException e) {
				e.printStackTrace();
				throw new UserProblemException("Can't return user.Please try again", e);
			} finally {
				try {
					if (ps != null)
						ps.close();
					if (rs != null)
						rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} else {
			throw new UserProblemException("Wrong Id");
		}
	}

	public List<User> getAllUsers() throws UserProblemException {
		Statement st = null;
		ResultSet rs = null;
		List<User> wantedUsers = new ArrayList<User>();
		try {
			st = getCon().createStatement();

			rs = st.executeQuery(SELECT_ALL_USERS);
			while (rs.next()) {
				wantedUsers.add(getWantedUser(rs));
			}
			return wantedUsers;
		} catch (SQLException | PictureFormatException | UserProblemException e) {
			e.printStackTrace();
			throw new UserProblemException("Can't return users.Please try again", e);
		} finally {
			try {
				if (st != null)
					st.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private User getWantedUser(ResultSet rs) throws SQLException, PictureFormatException, UserProblemException {
		User wantedUser;

		ICountryDAO country = (ICountryDAO) new CountryDAO();
		ILanguageDAO language = (ILanguageDAO) new LanguageDAO();
		int id = rs.getInt(1);
		String email = rs.getString(2);
		String fullName = rs.getString(4);
		String picture = rs.getString(5);
		int countryId = rs.getInt(6);
		int languageId = rs.getInt(7);
		String backgroundPicture = rs.getString(8);
		boolean isAdmin = rs.getBoolean(9);
		if (isAdmin == false) {
			wantedUser = new User(id, email, fullName);
		} else {
			wantedUser = new Admin(id, email, fullName);
		}
		wantedUser.setPicture(picture);
		if (countryId != 0) {
			wantedUser.setCountry(country.getCountryById(countryId));
		}
		wantedUser.setLanguage(language.getLanguageById(languageId));
		wantedUser.setBackgroundPicture(backgroundPicture);
		return wantedUser;
	}

	public void addPlaylistIntoLibrary(int playlistId, int userId) throws UserProblemException {
		PreparedStatement ps = null;
		try {
			ps = getCon().prepareStatement(ADD_INTO_LIBRARY);
			ps.setInt(1, userId);
			ps.setInt(2, playlistId);

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserProblemException("Can't add playlist.Please try again", e);
		} finally {
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void deletePlaylistFromLibrary(int playlistId, int userId) throws UserProblemException {
		PreparedStatement ps = null;
		try {
			ps = getCon().prepareStatement(DELETE_FROM_LIBRARY);
			ps.setInt(1, userId);
			ps.setInt(2, playlistId);

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserProblemException("Can't remove users playlist", e);
		} finally {
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public List<Playlist> getAllPlaylistsForUser(int userId) throws UserProblemException {
		List<Playlist> playlists = new ArrayList<Playlist>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = getCon().prepareStatement(SELECT_PLAYLISTS_FROM_LIBRARY);
			ps.setInt(1, userId);

			rs = ps.executeQuery();

			PlaylistDAO playlist = new PlaylistDAO();

			while (rs.next()) {
				Playlist wantedPlaylist = playlist.getPlaylistById(rs.getInt(1));
				playlists.add(wantedPlaylist);
			}
			return playlists;
		} catch (SQLException | PlaylistException e) {
			e.printStackTrace();
			throw new UserProblemException("There is a problem.We can't get you back your playlists for now", e);
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void addLike(int userId, int clipId, int preference) throws UserProblemException {
		PreparedStatement ps = null;
		try {
			ps = getCon().prepareStatement(ADD_INTO_LIKES);
			ps.setInt(1, userId);
			ps.setInt(2, clipId);
			ps.setInt(3, preference);

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserProblemException("Like doesn't insert", e);
		} finally {
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void updateLike(int userId, int clipId, int preference) throws UserProblemException {
		PreparedStatement ps = null;
		try {
			ps = getCon().prepareStatement(UPDATE_LIKES);
			ps.setInt(1, preference);
			ps.setInt(2, userId);
			ps.setInt(3, clipId);

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserProblemException("Like doesn't update", e);
		} finally {
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void deleteLike(int userId, int clipId) throws UserProblemException {
		PreparedStatement ps = null;
		try {
			ps = getCon().prepareStatement(DELETE_FROM_LIKES);
			ps.setInt(1, userId);
			ps.setInt(2, clipId);

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserProblemException("Like doesn't delete", e);
		} finally {
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public int getCountFromLikes(int clipId, int preference) throws UserProblemException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		try {
			ps = getCon().prepareStatement(SELECT_COUNT_LIKES_OR_DISLIKES);
			ps.setInt(1, clipId);
			ps.setInt(2, preference);

			rs = ps.executeQuery();
			rs.next();
			count = rs.getInt(1);
			return count;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserProblemException("Can't count likes/dislikes", e);
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public int getLikeIfExist(int clipId, int userId) throws UserProblemException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		int state = 0;
		try {
			ps = getCon().prepareStatement("Select preference from likes where clip_id=? and user_id=?;");
			ps.setInt(1, clipId);
			ps.setInt(2, userId);

			rs = ps.executeQuery();
			if (rs.next()) {
				state = rs.getInt(1);
			}
			return state;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserProblemException("Can't count likes/dislikes", e);
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void addClipToHistory(int clipID, int userID) throws UserProblemException {
		PreparedStatement ps = null;
		try {
			ps = getCon().prepareStatement(ADD_TO_HISTORY);
			ps.setInt(1, userID);
			ps.setInt(2, clipID);

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserProblemException("Can't insert into history", e);
		} finally {
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void deleteClipFromHistory(int clipID, int userID) throws UserProblemException {
		PreparedStatement ps = null;
		try {
			ps = getCon().prepareStatement(DELETE_CLIP_FROM_HISTORY);
			ps.setInt(1, userID);
			ps.setInt(2, clipID);

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserProblemException("Can't remove clip", e);
		} finally {
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void deleteAllClipsFromHistory(int userID) throws UserProblemException {
		PreparedStatement ps = null;
		try {
			ps = getCon().prepareStatement(DELETE_ALL_CLIPS_OF_USER);
			ps.setInt(1, userID);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserProblemException("Can't remove clips", e);
		} finally {
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (ps != null)
						ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public List<Clip> getHistory(int userID) throws UserProblemException {
		List<Clip> history = new ArrayList<Clip>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = getCon().prepareStatement(GET_HISTORY);
			ps.setInt(1, userID);

			rs = ps.executeQuery();

			ClipDAO clipDAO = new ClipDAO();
			while (rs.next()) {
				history.add(clipDAO.getClipByID(rs.getInt(1)));
			}
			return history;
		} catch (SQLException | ClipException e) {
			e.printStackTrace();
			throw new UserProblemException("There is a problem.We can't get your history", e);
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
