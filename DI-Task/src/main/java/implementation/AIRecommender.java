package implementation;

import beans.Course;
import interfaces.CourseRecommender;
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
                new Course("AI Recommender 1 "),
                new Course("AI Recommender 2  ")
        );
    }
}
