package com.example.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentDAO extends AbstractDAO {

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
				int id = rs.getInt(1);
				int clipsID = rs.getInt(2);
				String description = rs.getString(3);
				Comment comment = new Comment(id, clipsID, description);
				mainComments.add(comment);
			}
			return mainComments;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new CommentException("No comments to show! ");
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
				int id = rs.getInt(1);
				int clipsID = rs.getInt(2);
				String description = rs.getString(3);
				Comment comment = new Comment(id, clipsID, description);
				answerComments.add(comment);
			}
			return answerComments;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new CommentException("No answer comments to show! ");
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

	public void removeComent(int commentId) throws CommentException {
		PreparedStatement ps = null;
		try {
			ps = getCon().prepareStatement(DELETE_COMMENT);
			ps.setInt(1, commentId);
			ps.executeUpdate();
		} catch (Exception e) {
			throw new CommentException("Problem with comment removing", e);
		} finally {
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
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
			e.printStackTrace();
			throw new CommentException("Can't add main comment", e);
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
			e.printStackTrace();
			throw new CommentException("Can't add answer comment", e);
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
	}

}
