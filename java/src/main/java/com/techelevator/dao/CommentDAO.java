package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.Comment;

public interface CommentDAO {
	
	List<Comment> findCommentsForPhoto(int photoId);
	
}
