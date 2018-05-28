package com.videocean.tests;

import org.junit.Test;

import com.videocean.model.Clip;
import com.videocean.service.dao.ClipDAO;
import com.videocean.exception.ClipException;
import com.videocean.model.Comment;
import com.videocean.service.dao.CommentDAO;
import com.videocean.exception.CommentException;
import com.videocean.exception.UserException;

public class TestCommentDAO {

	CommentDAO commentDAO = new CommentDAO();

	@Test
	public void testAddDeleteComment() throws UserException, CommentException, ClipException {
		int forDelete;
		Clip clip = new ClipDAO().getClipByID(1);
		Comment comment = new Comment(1, clip, "hello world");
		Comment answerComment = new Comment(10, clip, "mello world");
		forDelete = commentDAO.addComment(comment);
		int toDelete = commentDAO.addAnswerComment(answerComment, forDelete);
		commentDAO.removeComent(toDelete);
		commentDAO.removeComent(forDelete);

	}

}
