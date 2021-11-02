SELECT comment_id, photo_id, comment.user_id, message, comment.date_time, username FROM comment JOIN users ON comment.user_id = users.user_id WHERE photo_id = ;

SELECT photo.photo_id, link, caption, likes_count, comment.date_time, cu.username, message, pu.username 
FROM photo JOIN comment ON photo.photo_id = comment.photo_id 
JOIN users AS cu ON comment.user_id = cu.user_id 
JOIN users AS pu ON photo.user_id = pu.user_id 
ORDER BY photo.photo_id DESC,  comment.date_time DESC;



SELECT photo.photo_id AS photo_id, link, caption, likes_count, comment.date_time AS comment_date_time, cu.username AS comment_username, message, pu.username AS photo_username FROM photo LEFT JOIN (
        select  photo_id, max(comment_id) as max_comment_id
        from    comment
        group by photo_id
) pcmax ON pcmax.photo_id = photo.photo_id
LEFT JOIN comment ON pcmax.max_comment_id = comment.comment_id 
LEFT JOIN users AS cu ON comment.user_id = cu.user_id 
LEFT JOIN users AS pu ON photo.user_id = pu.user_id 
ORDER BY photo.photo_id DESC;


select  distinct c1.*
from    comment c1 JOIN (
                select  photo_id, max(comment_id) as max_comment_id
                from    comment
                group by photo_id
        ) c2 ON c1.comment_id = c2.max_comment_id
;


select  *
from    photo
join users as u1 ON u1.user_id = photo.user_id
left join comment c1 on c1.photo_id = photo.photo_id

;

SELECT * FROM photo ORDER BY date_time DESC LIMIT 10;
