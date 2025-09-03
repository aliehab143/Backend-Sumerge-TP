package main;

import config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import services.CourseService;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        CourseService courseService = context.getBean(CourseService.class);
        System.out.println(courseService.getRecommendedCourses());
    }
}
