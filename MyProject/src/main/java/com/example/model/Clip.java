package com.example.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class Clip implements IClip {

	private int clipID;
	private String name;
	private IUser owner;
	private int likes;
	private int dislikes;
	private Category category;
	private String clipURL;

	private List<Comment> comments;
	private TYPE state;
	private LocalDate datePublished;
	private String description;
	private int views;
	private Statistics statisticsForClip;

	public Clip(String name, IUser owner, String clipURL, TYPE state) throws ClipException {
		if (name != null && owner != null && clipURL != null) {
			this.name = name;
			this.owner = owner;
			this.clipURL = clipURL;
			this.state = state;
			this.datePublished = LocalDate.now();
			this.likes = 0;
			this.dislikes = 0;
			comments = new ArrayList<Comment>();
		} else {
			throw new ClipException("Invalid arguments!");
		}
	}

	public Clip(int id, String name, IUser owner, String clipURL, TYPE state) throws ClipException {
		this.clipID = id;
		this.name = name;
		this.owner = owner;
		this.clipURL = clipURL;
		this.state = state;
		comments = new ArrayList<Comment>();
	}

	public Clip() {
		// TODO Auto-generated constructor stub
	}

	public void setViews(int views) {
		if (views >= 0) {
			this.views = views;
		} else {
			this.views = 0;
		}

	}

	public void setName(String name) {
		this.name = name;
	}

	public void setOwner(IUser owner) {
		this.owner = owner;
	}

	public void setClipURL(String clipURL) {
		this.clipURL = clipURL;
	}

	public void setDatePublished(LocalDate datePublished) {
		this.datePublished = datePublished;
	}

	public int getLikes() {
		return likes;
	}

	@Override
	public void addLike() {
		this.likes += 1;
	}

	public void removeLike() {
		this.likes -= 1;
	}

	public int getDislikes() {
		return dislikes;
	}

	@Override
	public void addDislike() {
		this.dislikes += 1;
	}

	public void removeDislike() {
		this.dislikes -= 1;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		if (category != null)
			this.category = category;
	}

	public TYPE getState() {
		return state;
	}

	public void setState(TYPE state) {
		if (state != null)
			this.state = state;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		if (description != null)
			this.description = description;
	}

	public int getViews() {
		return views;
	}

	@Override
	public void addViews() {
		if (views >= 0)
			this.views++;
	}

	public Statistics getStatisticsForClip() {
		return statisticsForClip;
	}

	public void setStatisticsForClip(Statistics statisticsForClip) {
		if (statisticsForClip != null)
			this.statisticsForClip = statisticsForClip;
	}

	public String getName() {
		return name;
	}

	public IUser getOwner() {
		return owner;
	}

	public String getClipURL() {
		return clipURL;
	}

	public LocalDate getDatePublished() {
		return datePublished;
	}

	@Override
	public void addComment(Comment comment) throws CommentException {
		if (comment != null) {
			comments.add(comment);
		} else {
			throw new CommentException("No such comment! Please rethink!");
		}
	}

	@Override
	public void removeComment(Comment comment) throws CommentException {
		if (comment != null) {
			comments.remove(comment);
		}
		throw new CommentException("No such comment! Please rethink!");
	}

	@Override
	public void addAnswer(Comment answer, Comment comment) throws CommentException {
		if (comment != null && answer != null) {
			if (comments.contains(comment)) {
				for (Comment commentMain : comments) {
					if (commentMain.equals(comment)) {
						commentMain.addAnswerComment(answer);
						break;
					}
				}
			} else {
				throw new CommentException("No such comment! Please rethink!");
			}
		}
	}

	@Override
	public void removeAnswer(Comment answer, Comment comment) throws CommentException {
		if (comment != null && answer != null) {
			if (comments.contains(comment)) {
				for (Comment commentMain : comments) {
					if (commentMain.equals(comment)) {
						commentMain.removeAnswerComment(answer);
						break;
					}
				}
			} else {
				throw new CommentException("No such comment! Please rethink!");
			}
		}
	}

	public int getClipID() {
		return clipID;
	}

	public void setClipID(int clipID) {
		this.clipID = clipID;
	}
}
