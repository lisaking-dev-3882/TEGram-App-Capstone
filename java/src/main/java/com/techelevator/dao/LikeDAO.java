package com.techelevator.dao;

public interface LikeDAO {
	
	boolean create (int userId, int photoId);
	
	boolean delete (int userId, int photoId);
	
}
