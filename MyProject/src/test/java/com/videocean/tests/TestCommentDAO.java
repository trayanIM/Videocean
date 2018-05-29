package com.videocean.tests;

import com.videocean.model.TYPE;
import com.videocean.model.User;
import com.videocean.service.dao.UserDAO;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.videocean.model.Clip;
import com.videocean.service.dao.ClipDAO;
import com.videocean.exception.ClipException;
import com.videocean.model.Comment;
import com.videocean.service.dao.CommentDAO;
import com.videocean.exception.CommentException;
import com.videocean.exception.UserException;
@Ignore
public class TestCommentDAO {

	private int userId;

	private CommentDAO commentDAO = new CommentDAO();
	private UserDAO userDAO = new UserDAO();
	private ClipDAO clipDAO = new ClipDAO();
	private int clipId;

	@Before
	public void setUp() throws UserException, ClipException {
		User user = new User("trayanI@gmail.com", "1234567", "Muchev");
		this.userId = userDAO.addUser(user);
		clipId = clipDAO.addClip(new Clip("Name", userDAO.getUserById(userId), "URL", TYPE.PUBLIC));
	}

	@After
	public void tearDown() throws UserException, ClipException {
		clipDAO.removeClip(clipId);
		userDAO.removeUser(userId);

	}

	@Test
	public void testAddDeleteComment() throws UserException, CommentException, ClipException {
		Clip clip = new ClipDAO().getClipByID(clipId);
		Comment comment = new Comment(10000, clip, "hello world");
		Comment answerComment = new Comment(10001, clip, "mello world");
		int commentForDelete = commentDAO.addComment(comment);
		int answerForDelete = commentDAO.addAnswerComment(answerComment, commentForDelete);
		commentDAO.removeComent(answerForDelete);
		commentDAO.removeComent(commentForDelete);

	}

}
