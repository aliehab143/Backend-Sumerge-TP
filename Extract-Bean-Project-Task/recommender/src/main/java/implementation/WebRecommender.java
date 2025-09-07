package implementation;

import model.Course;
import interfaces.CourseRecommender;

import java.util.Arrays;
import java.util.List;


public class WebRecommender implements CourseRecommender {

    @Override
    public List<Course> recommendedCourses() {
        return Arrays.asList(
                new Course("WebRecommender 1"),
                new Course("WebRecommender 2")
        );
    }
}
