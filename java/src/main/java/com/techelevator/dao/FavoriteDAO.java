package com.techelevator.dao;

public interface FavoriteDAO {

	boolean create (int userId, int photoId);
	
	boolean delete (int userId, int photoId);

}
