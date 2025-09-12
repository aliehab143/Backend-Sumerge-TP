package com.example.RestController.service;

import com.example.RestController.interfaces.CourseRecommender;
import com.example.RestController.model.Course;
import com.example.RestController.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseRecommender courseRecommender;

    public CourseService(CourseRepository courseRepository, CourseRecommender courseRecommender) {
        this.courseRepository = courseRepository;
        this.courseRecommender = courseRecommender;
    }

    public List<Course> viewAllCourses() {
        return courseRepository.findAll();
    }

    public Course viewCourse(Long id) {
        return courseRepository.findById(id);
    }

    public Course getCourseById(Long id) {
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

    public List<Course> discoverAllCourses() {
        return courseRecommender.recommendedCourses();
    }
}