package com.techelevator.dao;

import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.techelevator.model.Photo;

@Service
public class PhotoSqlDAO implements PhotoDAO {
	
	private static final int INITIAL_LIKES_COUNT = 0;
	
	private JdbcTemplate jdbcTemplate;
	
	public PhotoSqlDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Photo> getAllPhotos() {

	   List<Photo> listOfPhotos = new ArrayList<Photo>();
	   String sql = "select * from photo";
	   SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
	   while (results.next()) {
	      Photo photo = mapRowToPhoto(results);
	      listOfPhotos.add(photo);
	   }
	   return listOfPhotos;
	}

	@Override
	public List<Photo> getPhotoByUserId(int user_id) {
		List<Photo> listOfPhotos = new ArrayList<Photo>();
		String sql = "select * from photo where user_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, user_id);

		while (results.next()) {
			Photo photo = mapRowToPhoto(results);
			listOfPhotos.add(photo);
		}
		return listOfPhotos;
	}

	@Override
	public List<Photo> findTen() {
		List<Photo> photos = new ArrayList<>();
		String sql = "SELECT * FROM photo ORDER BY date_time DESC LIMIT 10";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		while(results.next()) {
			Photo photo = mapRowToPhoto(results);
			photos.add(photo);
		}
		
		return photos;
	}

	@Override
	public Photo getPhotoById(int photoId) {
		String sql = "SELECT * FROM photo WHERE photo_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, photoId);
		if(results.next()) {
			return mapRowToPhoto(results);
		} else {
			throw new RuntimeException("Photo ID " + photoId + "was not found.");
		}
	}

	@Override
	public List<Photo> getFavoritePhotos(int userId) {
		List<Photo> photos = new ArrayList<>();
		String sql = "SELECT * FROM photo JOIN favorites ON photo.photo_id = favorites.photo_id "
				+ "WHERE favorites.user_id  = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
		while(results.next()) {
			Photo photo = mapRowToPhoto(results);
			photos.add(photo);
		}
		
		return photos;
	}
	
	@Override
	public boolean like(int photoId) {
		boolean likesUpdated = false;
		String insertLikesNumber = "UPDATE photo SET likes_count = (likes_count + 1) WHERE photo_id = ?";
		likesUpdated = jdbcTemplate.update(insertLikesNumber, photoId) == 1;
		return likesUpdated;
	}

	@Override
	public boolean unlike(int photoId) {
		boolean likesUpdated = false;
		String insertLikesNumber = "UPDATE photo SET likes_count = (likes_count - 1) WHERE photo_id = ?";
		likesUpdated = jdbcTemplate.update(insertLikesNumber, photoId) == 1;
		return likesUpdated;
	}
	
	@Override
	public boolean create(int user_id, String fileName, String link, String caption) {
		boolean photoCreated = false;
		
		LocalDateTime dateTime = LocalDateTime.now();
		String insertPhoto = "INSERT INTO photo (user_id, file_name, link, caption, likes_count, date_time) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";
		photoCreated = jdbcTemplate.update(con -> {
			PreparedStatement ps = con.prepareStatement(insertPhoto);
			ps.setInt(1, user_id);
			ps.setString(2, fileName);
			ps.setString(3, link);
			ps.setString(4, caption);
			ps.setInt(5, INITIAL_LIKES_COUNT);
			ps.setObject(6, dateTime);			
			return ps;
		}) == 1;		
		
		return photoCreated;
	}

	private Photo mapRowToPhoto(SqlRowSet rs) {
		Photo photo = new Photo();
		photo.setId(rs.getInt("photo_id"));
		photo.setUserId(rs.getInt("user_id"));
		photo.setFileName(rs.getString("file_name"));
		photo.setLink(rs.getString("link"));
		photo.setCaption(rs.getString("caption"));
		photo.setLikesCount(rs.getInt("likes_count"));
		photo.setDateTime(rs.getDate("date_time"));		
		return photo;
	}
	
}
