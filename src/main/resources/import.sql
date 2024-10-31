INSERT INTO students (identity_card_number, file_number, name, surname) VALUES('35600900', '11111', 'Ale', 'San');
INSERT INTO students (identity_card_number, file_number, name, surname) VALUES('34200800', '22222', 'John', 'Doe');
INSERT INTO professors (identity_card_number, name, surname, active) VALUES('10895615', 'Pepe', 'Fonewman', '0');
INSERT INTO professors (identity_card_number, name, surname, active) VALUES('14865161', 'Mariana', 'Dupont', '0');
INSERT INTO professors (identity_card_number, name, surname, active) VALUES('22467458', 'Javier', 'Grant', '1');
INSERT INTO professors (identity_card_number, name, surname, active) VALUES('18724375', 'Daniel', 'Cluster', '1');
INSERT INTO professors (identity_card_number, name, surname, active) VALUES('13598642', 'Susana', 'Marchand', '1');
INSERT INTO subjects (name, description, timetable, enrolled, max_quota, id_professor) VALUES('Programming', 'Learning the concepts of programming language', 'Monday 08:00', '0', '20', null);
INSERT INTO subjects (name, description, timetable, enrolled, max_quota, id_professor) VALUES('Mathematics', 'Knowing the basics of trigonometry, logarithms and more', 'Friday 08:00', '0', '15', null);
INSERT INTO subjects (name, description, timetable, enrolled, max_quota, id_professor) VALUES('English', 'Focusing on the development of reading, writing, speaking and listening skills', 'Friday 08:00', '0', '5', null);
INSERT INTO subjects (name, description, timetable, enrolled, max_quota, id_professor) VALUES('Philosophy', 'Study of fundamental questions and concepts that shape human understanding of existence, knowledge, values, reason, mind, and language', 'Tuesday 10:00', '0', '0', null);
INSERT INTO users (username, password, role) VALUES('35600900', '11111', 'Student');
INSERT INTO users (username, password, role) VALUES('34200800', '22222', 'Student');
INSERT INTO users (username, password, role) VALUES('admin', '12345', 'Admin');