package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.Photo;

public interface PhotoDAO {

	List<Photo> findTen();

	List<Photo> getAllPhotos();

	Photo getPhotoById(int photoId);
	
	boolean create(int user_id, String fileName, String link, String caption);

	List<Photo> getPhotoByUserId(int user_id);

	List<Photo> getFavoritePhotos(int user_id);
	
	public boolean like(int photoId);

	public boolean unlike(int photoId);
}
