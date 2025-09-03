package interfaces;

import beans.Course;

import java.util.List;

public interface CourseRecommender {
    List<Course> recommendedCourses();
}
