INSERT INTO favorites (photo_id, user_id, date_time) VALUES (3,4, '2021-03-12 08:03:50');
INSERT INTO favorites (photo_id, user_id, date_time) VALUES (4,4, '2021-03-12 08:03:50');
INSERT INTO favorites (photo_id, user_id, date_time) VALUES (5,4, '2021-03-12 08:03:50');
INSERT INTO favorites (photo_id, user_id, date_time) VALUES (6,4, '2021-03-12 08:03:50');
INSERT INTO favorites (photo_id, user_id, date_time) VALUES (7,4, '2021-03-12 08:03:50');
INSERT INTO favorites (photo_id, user_id, date_time) VALUES (8,4, '2021-03-12 08:03:50');
INSERT INTO favorites (photo_id, user_id, date_time) VALUES (9,5, '2021-03-12 08:03:50');

UPDATE photo SET likes_count = 789 WHERE photo_id = 96;
INSERT INTO likes (photo_id, user_id, date_time) VALUES (4,3, '2021-04-12 08:03:50');
INSERT INTO likes (photo_id, user_id, date_time) VALUES (4,2, '2021-05-12 08:03:50');
INSERT INTO likes (photo_id, user_id, date_time) VALUES (4,1, '2021-06-12 08:03:50');
INSERT INTO likes (photo_id, user_id, date_time) VALUES (5,4, '2021-07-12 08:03:50');
select * from favorites;
select * from likes;
select * from photo;
select * from comment;

DELETE FROM favorites WHERE photo_id = 9 AND user_id  = 4;

SELECT * FROM photo JOIN favorites ON photo.photo_id = favorites.photo_id WHERE favorites.user_id  = 4;

select * from photo;

DELETE FROM photo WHERE photo_id = 20 AND user_id  = 6;
GRANT ALL
ON ALL TABLES IN SCHEMA public
TO final_capstone_owner;

GRANT ALL
ON ALL SEQUENCES IN SCHEMA public
TO final_capstone_owner;



GRANT SELECT, INSERT, UPDATE, DELETE
ON ALL TABLES IN SCHEMA public
TO final_capstone_appuser;

GRANT USAGE, SELECT
ON ALL SEQUENCES IN SCHEMA public
TO final_capstone_appuser

select * from photo p, favorites f where p.photo_id=f.photo_id AND f.user_id=12;

INSERT INTO favorites (photo_id, user_id, date_time) VALUES (15, 12, '2021-03-12 08:03:50');
INSERT INTO favorites (photo_id, user_id, date_time) VALUES (16, 12, '2021-03-12 08:03:50');
INSERT INTO favorites (photo_id, user_id, date_time) VALUES (80, 12, '2021-03-12 08:03:50');
