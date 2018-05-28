package com.example.model;

public interface IClip {

	void addLike();
	
	void removeLike();

	void addDislike();
	
	void removeDislike();

	void addViews();

	void addComment(Comment comment) throws CommentException;

	void removeComment(Comment comment) throws CommentException;

	void removeAnswer(Comment answer, Comment comment) throws CommentException;



	void addAnswer(Comment answer, Comment comment) throws CommentException;

}