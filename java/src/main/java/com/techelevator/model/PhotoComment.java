package com.techelevator.model;

import java.util.Date;

public class PhotoComment {
	private int id;
	private String link;
	private String caption;
	private int likesCount;
	private Date CommentDateTime;
	private String message;
	private String photoUsername;
	private String commentUsername;
	
	public int getId() {
		return id;
	}
	public void setId(int photoId) {
		this.id = photoId;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public int getLikesCount() {
		return likesCount;
	}
	public void setLikesCount(int likesCount) {
		this.likesCount = likesCount;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getPhotoUsername() {
		return photoUsername;
	}
	public void setPhotoUsername(String photoUsername) {
		this.photoUsername = photoUsername;
	}
	public String getCommentUsername() {
		return commentUsername;
	}
	public void setCommentUsername(String commentUsername) {
		this.commentUsername = commentUsername;
	}
	public Date getCommentDateTime() {
		return CommentDateTime;
	}
	public void setCommentDateTime(Date commentDateTime) {
		CommentDateTime = commentDateTime;
	}
	
}
