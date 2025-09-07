package implementation;

import model.Course;
import interfaces.CourseRecommender;


import java.util.Arrays;
import java.util.List;


public class WebRecommender implements CourseRecommender { // here im overriding making step4 in the task

    @Override
    public List<Course> recommendedCourses() {
        return Arrays.asList(
                new Course("WebRecommender 1"),
                new Course("im overriding ")
        );
    }
}
