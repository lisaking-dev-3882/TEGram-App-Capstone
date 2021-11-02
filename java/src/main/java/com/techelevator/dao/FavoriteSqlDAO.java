package com.techelevator.dao;

import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class FavoriteSqlDAO implements FavoriteDAO {

	private JdbcTemplate jdbcTemplate;

	public FavoriteSqlDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public boolean create(int userId, int photoId) {
		boolean favoriteCreated = false;
		LocalDateTime dateTime = LocalDateTime.now();
		String insertFavorite = "INSERT INTO favorites (photo_id, user_id, date_time) VALUES (?, ?, ?)";
		favoriteCreated = jdbcTemplate.update(con -> {
			PreparedStatement ps = con.prepareStatement(insertFavorite);
			ps.setInt(1, photoId);
			ps.setInt(2, userId);
			ps.setObject(3, dateTime);
			return ps;
		}) == 1;

		return favoriteCreated;
	}

	@Override
	public boolean delete(int userId, int photoId) {
		boolean favoriteDeleted = false;
		String sql = "DELETE FROM favorites WHERE photo_id = ? AND user_id  = ?";
		favoriteDeleted = jdbcTemplate.update(sql, photoId, userId) == 1;
		return favoriteDeleted;
	}

}
