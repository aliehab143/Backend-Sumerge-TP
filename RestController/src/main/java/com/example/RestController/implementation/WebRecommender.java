package com.example.RestController.implementation;

import com.example.RestController.model.Course;
import com.example.RestController.interfaces.CourseRecommender;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component("webRecommender")
public class WebRecommender implements CourseRecommender {

    @Override
    public List<Course> recommendedCourses() {
        return Arrays.asList(
                new Course(3L, "Web Development Course 1", "Frontend Development with React", 3),
                new Course(4L, "Web Development Course 2", "Backend Development with Spring Boot", 4)
        );
    }
}
