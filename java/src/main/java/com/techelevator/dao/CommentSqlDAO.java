package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.techelevator.model.Comment;

@Service
public class CommentSqlDAO implements CommentDAO{

	private JdbcTemplate jdbcTemplate;
	
	public CommentSqlDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public List<Comment> findCommentsForPhoto(int photoId) {
		List<Comment> comments = new ArrayList<>();
		String sql = "SELECT comment_id, photo_id, comment.user_id, message, "
				+ "comment.date_time, username FROM comment JOIN users "
				+ "ON comment.user_id = users.user_id WHERE photo_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, photoId);
		while(results.next()) {
			Comment comment = mapRowToComment(results);
			comments.add(comment);
		}
		return comments;
	}

	private Comment mapRowToComment(SqlRowSet rs) {
		Comment comment = new Comment();
		comment.setId(rs.getInt("comment_id"));
		comment.setPhotoId(rs.getInt("photo_id"));
		comment.setUserId(rs.getInt("user_id"));
		comment.setMessage(rs.getString("message"));
		comment.setDateTime(rs.getDate("date_time"));
		comment.setUsername(rs.getString("username"));		
		return comment;
	}

	

}
