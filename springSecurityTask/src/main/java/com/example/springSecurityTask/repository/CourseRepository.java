package com.example.springSecurityTask.repository;

import com.example.springSecurityTask.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

    Page<Course> findByAuthorsEmail(String email, Pageable pageable);
}
