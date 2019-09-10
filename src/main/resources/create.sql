CREATE SCHEMA IF NOT EXISTS student_crud;

CREATE TABLE  IF NOT EXISTS student_crud.student(
    student_id INT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    email VARCHAR(255),
    student_number VARCHAR(255),
    PRIMARY KEY (student_id)
);

INSERT INTO student_crud.student (student_id, first_name, last_name, email, student_number) VALUES (1, 'Can', 'Berberoglu', 'can.berberoglu@gmail.com', '1234567');
INSERT INTO student_crud.student (student_id, first_name, last_name, email, student_number) VALUES (2, 'Cem', 'Berberoglu', 'cem.berberoglu@gmail.com', '5415875');
INSERT INTO student_crud.student (student_id, first_name, last_name, email, student_number) VALUES (3, 'Sinan', 'Yilmaz', 'sinan.yilmaz@gmail.com', '4845155');
INSERT INTO student_crud.student (student_id, first_name, last_name, email, student_number) VALUES (4, 'Suleyman', 'Zengin', 'suleyman.zengin@gmail.com', '8784521');
INSERT INTO student_crud.student (student_id, first_name, last_name, email, student_number) VALUES (5, 'Kadir', 'Sen', 'kadir.sen@gmail.com', '8848484');