DROP TABLE IF EXISTS ratings;
DROP TABLE IF EXISTS assessments;
DROP TABLE IF EXISTS author_courses;
DROP TABLE IF EXISTS courses;
DROP TABLE IF EXISTS authors;

CREATE TABLE authors (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(200) NOT NULL,
  email VARCHAR(200) NOT NULL UNIQUE,
  birthdate DATE
);

CREATE TABLE courses (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(200) NOT NULL,
  description VARCHAR(1000),
  credit INT NOT NULL CHECK (credit >= 0)
);

CREATE TABLE author_courses (
  author_id BIGINT NOT NULL,
  course_id BIGINT NOT NULL,
  PRIMARY KEY (author_id, course_id),
  CONSTRAINT fk_ac_author FOREIGN KEY (author_id) REFERENCES authors(id) ON DELETE CASCADE,
  CONSTRAINT fk_ac_course FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE
);

CREATE TABLE assessments (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  content VARCHAR(2000),
  course_id BIGINT NOT NULL,
  CONSTRAINT fk_assessment_course FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE
);

CREATE TABLE ratings (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  number INT NOT NULL CHECK (number BETWEEN 1 AND 5),
  course_id BIGINT NOT NULL,
  CONSTRAINT fk_rating_course FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE
);
