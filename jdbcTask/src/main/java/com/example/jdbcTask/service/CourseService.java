package com.example.jdbcTask.service;

import com.example.jdbcTask.model.Course;
import com.example.jdbcTask.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> viewAllCourses() {
        return courseRepository.findAll();
    }

    public Course viewCourse(Long id) {
        return courseRepository.findById(id);
    }

    public int addCourse(Course course) {
        return courseRepository.save(course);
    }

    public int updateCourse(Course course) {
        return courseRepository.update(course);
    }

    public int deleteCourse(Long id) {
        return courseRepository.delete(id);
    }
}