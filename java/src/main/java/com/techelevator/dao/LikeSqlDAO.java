package com.techelevator.dao;

import java.sql.PreparedStatement;
import java.time.LocalDateTime;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class LikeSqlDAO implements LikeDAO {

	private JdbcTemplate jdbcTemplate;

	public LikeSqlDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public boolean create(int userId, int photoId) {
		boolean liked = false;
		LocalDateTime dateTime = LocalDateTime.now();
		String insertLike = "INSERT INTO likes (photo_id, user_id, date_time) VALUES (?, ?, ?)";
		liked = jdbcTemplate.update(con -> {
			PreparedStatement ps = con.prepareStatement(insertLike);
			ps.setInt(1, photoId);
			ps.setInt(2, userId);
			ps.setObject(3, dateTime);
			return ps;
		}) == 1;
		return liked;
	}

	@Override
	public boolean delete(int userId, int photoId) {
		boolean unliked = false;
		String sql = "DELETE FROM likes WHERE photo_id = ? AND user_id  = ?";
		unliked = jdbcTemplate.update(sql, photoId, userId) == 1;
		return unliked;
	}

}
