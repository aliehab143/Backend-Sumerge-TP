package com.example.RestController.interfaces;

import com.example.RestController.model.Course;

import java.util.List;

public interface CourseRecommender {
    List<Course> recommendedCourses();
}
