SELECT comment_id, photo_id, user_id, message, date_time FROM comment;

INSERT INTO COMMENT(photo_id, user_id, message, date_time)
VALUES(83, 6, 'The future is bright!',  '2021-03-12 01:55:26');

select* from photo;
select * from comment;

select * from photo where photo_id = 10;
insert into photos(

UPDATE comment SET message='Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce sed iaculis neque.!'
WHERE photo_id = 1;
UPDATE comment SET message='Tech Elevator Rocks!'
WHERE photo_id = 19;
UPDATE comment SET message='Awesome! Congrats.'
WHERE photo_id = 78;

update comment set message='FABULOUS'
WHERE comment_id= 40;

delete from photo where photo_id =94;
select* from photo;
delete from comment message where photo_id = 1;

