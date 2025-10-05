
INSERT IGNORE INTO author (id, name, email, birthdate) VALUES 
(1, 'John Smith', 'john.smith@example.com', '1980-05-15'),
(2, 'Jane Doe', 'jane.doe@example.com', '1985-08-22'),
(3, 'Bob Johnson', 'bob.johnson@example.com', '1975-12-10');

INSERT IGNORE INTO course (id, name, description, credit) VALUES 
(1, 'Introduction to Spring Boot', 'Learn the fundamentals of Spring Boot framework for building Java applications', 3),
(2, 'Docker Containerization', 'Master Docker containers and orchestration for modern application deployment', 4),
(3, 'Database Design Principles', 'Comprehensive guide to relational database design and normalization', 3);

INSERT IGNORE INTO author_courses (author_id, course_id) VALUES 
(1, 1),  
(1, 2),  
(2, 2),  
(2, 3), 
(3, 3);  

INSERT IGNORE INTO assessment (id, content, course_id) VALUES 
(1, 'Create a simple REST API using Spring Boot', 1),
(2, 'Build and deploy a Docker container', 2),
(3, 'Design a normalized database schema', 3);

INSERT IGNORE INTO rating (id, number, course_id) VALUES 
(1, 5, 1),
(2, 4, 1), 
(3, 5, 2),
(4, 4, 2),
(5, 5, 3);
