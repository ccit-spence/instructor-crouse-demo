INSERT INTO course (id,created_date,modified_date) VALUES (1,'2015-05-29','2015-01-29');
INSERT INTO course (id,created_date,modified_date) VALUES (2,'2015-07-18','2015-06-19');
INSERT INTO course (id,created_date,modified_date) VALUES (3,'2014-11-29','2014-04-30');
INSERT INTO course (id,created_date,modified_date) VALUES (4,'2014-12-25','2014-06-24');
INSERT INTO course (id,created_date,modified_date) VALUES (5,'2015-04-15','2014-12-09');
INSERT INTO course (id,created_date,modified_date) VALUES (6,'2015-01-20','2014-06-26');
INSERT INTO course (id,created_date,modified_date) VALUES (7,'2014-08-20','2015-05-27');
INSERT INTO course (id,created_date,modified_date) VALUES (8,'2014-10-20','2015-03-30');
INSERT INTO course (id,created_date,modified_date) VALUES (9,'2014-05-24','2015-09-29');
INSERT INTO course (id,created_date,modified_date) VALUES (10,'2014-07-09','2014-07-28');

INSERT INTO related (id,rel_type,related_entity_id,course_id) VALUES (1,'instructors',1,1);
INSERT INTO related (id,rel_type,related_entity_id,course_id) VALUES (2,'instructors',1,2);
INSERT INTO related (id,rel_type,related_entity_id,course_id) VALUES (3,'instructors',2,1);
INSERT INTO related (id,rel_type,related_entity_id,course_id) VALUES (4,'instructors',2,3);