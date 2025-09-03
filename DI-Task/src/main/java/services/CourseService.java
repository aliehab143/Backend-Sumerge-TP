package services;

import beans.Course;
import interfaces.CourseRecommender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CourseService {
    private CourseRecommender courseRecommender;
    @Autowired
    public CourseService(@Qualifier("webRecommender") CourseRecommender courseRecommender) {
        this.courseRecommender = courseRecommender;
    }

   /*  Here another way to inject using setter  (the Injected will be the primary Bean ) To Fulfil the Task Requirements
     @Autowired
     public void setCourseRecommender(CourseRecommender courseRecommender) {
         this.courseRecommender = courseRecommender;
    }

    */
   public List<Course> getRecommendedCourses() {
       return courseRecommender.recommendedCourses();
   }
}
