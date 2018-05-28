package com.example.tests;

import org.junit.Test;

import com.example.model.Clip;
import com.example.model.ClipDAO;
import com.example.model.ClipException;
import com.example.model.Comment;
import com.example.model.CommentDAO;
import com.example.model.CommentException;
import com.example.model.UserProblemException;

public class TestCommentDAO {

	CommentDAO commentDAO = new CommentDAO();

	@Test
	public void testAddDeleteComment() throws UserProblemException, CommentException, ClipException {
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
