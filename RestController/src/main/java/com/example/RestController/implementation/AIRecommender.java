package com.example.RestController.implementation;

import com.example.RestController.model.Course;
import com.example.RestController.interfaces.CourseRecommender;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Primary
public class AIRecommender implements CourseRecommender {

    @Override
    public List<Course> recommendedCourses() {
        return Arrays.asList(
                new Course(1L, "AI Recommender Course 1", "Advanced AI and Machine Learning", 3),
                new Course(2L, "AI Recommender Course 2", "Deep Learning Fundamentals", 4)
        );
    }
}
