INSERT INTO comment (photo_id, user_id, message, date_time)
VALUES (4, 1, 'So cool! Is it tough?', '2021-03-25 4:41:33');
INSERT INTO comment (photo_id, user_id, message, date_time)
VALUES (5, 2, 'Suspendisse suscipit semper ante a posuere. Integer sapien lacus, ullamcorper eu diam nec, egestas malesuada est.', '2020-01-13 17:09:42');
INSERT INTO comment (photo_id, user_id, message, date_time)
VALUES (6, 3, 'Vestibulum ultricies, metus at porttitor aliquam, sapien augue pellentesque purus, eu tempor odio metus eu lectus. Duis sed metus neque.', '2021-01-13 17:09:42');
INSERT INTO comment (photo_id, user_id, message, date_time)
VALUES (7, 2, 'Nulla nisi ex, congue at mauris non, pretium mollis magna.', '2019-01-13 17:09:42');
INSERT INTO comment (photo_id, user_id, message, date_time)
VALUES (8, 1, 'Pellentesque ut elit vitae arcu ullamcorper porta.', '2019-05-13 17:09:42');
INSERT INTO comment (photo_id, user_id, message, date_time)
VALUES (9, 3, 'Etiam varius dui tellus, ut faucibus orci accumsan eget.', '2019-04-13 17:09:42');
INSERT INTO comment (photo_id, user_id, message, date_time)
VALUES (10, 1, 'Morbi enim ipsum, commodo non condimentum at, maximus id turpis.', '2019-06-13 17:09:42');

select * from comment;
select * from photo;
UPDATE comment SET message = "So cool! Is it tough?" WHERE photo_id = 96;
delete from photo where photo_id = 1 AND user_id = 6;