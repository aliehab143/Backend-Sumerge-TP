package implementation;

import beans.Course;
import interfaces.CourseRecommender;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component("webRecommender")
public class WebRecommender implements CourseRecommender {

    @Override
    public List<Course> recommendedCourses() {
        return Arrays.asList(
                new Course("WebRecommender 1"),
                new Course("WebRecommender 2")
        );
    }
}
