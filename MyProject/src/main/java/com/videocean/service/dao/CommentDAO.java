package com.videocean.service.dao;

import com.videocean.service.dbconnection.AbstractDAO;
import com.videocean.model.Comment;
import com.videocean.exception.CommentException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class CommentDAO extends AbstractDAO {

	private Logger logger = Logger.getLogger(CommentDAO.class.getName());

	private static final String ADD_ANSWER_COMMENT = "INSERT INTO comments VALUES(NULL,?,?,?);";
	private static final String ADD_MAIN_COMMENT = "INSERT INTO comments VALUES(NULL,?,?,NULL);";
	private static final String SELECT_MAIN_COMMENT = "SELECT * FROM comments WHERE clip_id=? AND answer_comment_id IS NULL ;";
	private static final String SELECT_ASNWER_COMMENTS = "SELECT * FROM comments WHERE clip_id=? AND answer_comment_id= ? ;";
	private static final String DELETE_COMMENT = "DELETE FROM comments WHERE comment_id = ?;";

	public List<Comment> getListMainComments(int clipID) throws CommentException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = getCon().prepareStatement(SELECT_MAIN_COMMENT);

			ps.setInt(1, clipID);
			rs = ps.executeQuery();
			List<Comment> mainComments = new ArrayList<Comment>();

			while (rs.next()) {
				Comment comment = getComment(rs);
				mainComments.add(comment);
			}
			return mainComments;
		} catch (SQLException e) {
			String errorMessage = "Comments have not been found! ";
			logger.info(errorMessage);
			throw new CommentException(errorMessage);
		} finally {
			CategoryDAO.closeConnection(ps, rs);
		}
	}

	private Comment getComment(ResultSet rs) throws SQLException {
		int id = rs.getInt(1);
		int clipsID = rs.getInt(2);
		String description = rs.getString(3);
		return new Comment(id, clipsID, description);
	}

	public List<Comment> getListAnswerComments(int clipID, int commentID) throws CommentException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = getCon().prepareStatement(SELECT_ASNWER_COMMENTS);

			ps.setInt(1, clipID);
			ps.setInt(2, commentID);
			rs = ps.executeQuery();
			List<Comment> answerComments = new ArrayList<Comment>();

			while (rs.next()) {
				Comment comment = getComment(rs);
				answerComments.add(comment);
			}
			return answerComments;
		} catch (SQLException e) {
			String errorMessage = "Comments answers have not been found! ";
			logger.info(errorMessage);
			throw new CommentException(errorMessage);
		} finally {
			CategoryDAO.closeConnection(ps, rs);
		}
	}

	public void removeComent(int commentId) throws CommentException {
		PreparedStatement ps = null;
		try {
			ps = getCon().prepareStatement(DELETE_COMMENT);
			ps.setInt(1, commentId);
			ps.executeUpdate();
		} catch (Exception e) {
			throw new CommentException("A problem appear during comment removal! ", e);
		} finally {
			closeConnection(ps);
		}
	}

	public int addComment(Comment comment) throws CommentException {
		PreparedStatement ps = null;
		ResultSet id = null;
		try {
			ps = getCon().prepareStatement(ADD_MAIN_COMMENT, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(1, comment.getThisClip().getClipID());
			ps.setString(2, comment.getCommentDescription());

			ps.executeUpdate();
			id = ps.getGeneratedKeys();
			id.next();
			return id.getInt(1);
		} catch (SQLException e) {
			String errorMessage = "Adding comment has failed!";
			logger.info(errorMessage);
			throw new CommentException(errorMessage, e);
		} finally {
			CategoryDAO.closeConnection(ps, id);
		}
	}

	public int addAnswerComment(Comment comment, int mainCommentId) throws CommentException {
		PreparedStatement ps = null;
		ResultSet id = null;
		try {
			ps = getCon().prepareStatement(ADD_ANSWER_COMMENT, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(1, comment.getThisClip().getClipID());
			ps.setString(2, comment.getCommentDescription());
			ps.setInt(3, mainCommentId);

			ps.executeUpdate();
			id = ps.getGeneratedKeys();
			id.next();
			return id.getInt(1);
		} catch (SQLException e) {
			String errorMessage = "Adding comment answer has failed!";
			logger.info(errorMessage);
			throw new CommentException(errorMessage, e);
		} finally {
			CategoryDAO.closeConnection(ps, id);
		}
	}

}
