package com.example.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//TOVA TRQBVA LI IZOBSHTO?!
public class Comment {

	private int commentID;
	private Clip thisClip;
	private String commentDescription;
	private List<Comment> answerComments;

	public Comment(int commentID, Clip thisClip, String commentDescription) {
		this.commentID = commentID;
		this.thisClip = thisClip;
		this.commentDescription = commentDescription;
		this.answerComments = new ArrayList<Comment>();
	}

	public Comment(int id, int clipsID, String description) {
		this.commentID = id;
		this.thisClip.setClipID(clipsID);
		this.commentDescription = description;
	}

	public void addAnswerComment(Comment comment) {
		if (comment != null)
			this.answerComments.add(comment);
	}

	public void removeAnswerComment(Comment comment) {
		if (comment != null) {
			if (answerComments.contains(comment)) {
				this.answerComments.remove(comment);
			}
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((commentDescription == null) ? 0 : commentDescription.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comment other = (Comment) obj;
		if (commentDescription == null) {
			if (other.commentDescription != null)
				return false;
		} else if (!commentDescription.equals(other.commentDescription))
			return false;
		return true;
	}

	public int getCommentID() {
		return commentID;
	}

	public void setCommentID(int commentID) {
		this.commentID = commentID;
	}

	public Clip getThisClip() {
		return thisClip;
	}

	public void setThisClip(Clip thisClip) {
		this.thisClip = thisClip;
	}

	public String getCommentDescription() {
		return commentDescription;
	}

	public void setCommentDescription(String commentDescription) {
		this.commentDescription = commentDescription;
	}

	public List<Comment> getAnswerComments() {
		return (List<Comment>) Collections.unmodifiableCollection(answerComments);
	}

}
