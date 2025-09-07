package implementation;

import interfaces.CourseRecommender;
import model.Course;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
;


@Primary
public class AIRecommender implements CourseRecommender {

    @Override
    public List<model.Course> recommendedCourses() {
        return Arrays.asList(
                new Course("AI Recommender 1 "),
                new Course("AI Recommender 2  ")
        );
    }
}
