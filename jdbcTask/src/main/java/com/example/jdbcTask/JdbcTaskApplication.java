package com.example.jdbcTask;

import com.example.jdbcTask.model.Course;
import com.example.jdbcTask.service.CourseService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JdbcTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(JdbcTaskApplication.class, args);
	}


    @Bean
    public CommandLineRunner demo(CourseService courseService) {
        return args -> {

            Course course1 = new Course(null, "test1", "test1", 1);
            courseService.addCourse(course1);

            Course course2 = new Course(null, "test2", "test2", 1);
            courseService.addCourse(course2);

            System.out.println("All Courses:");
            courseService.viewAllCourses().forEach(System.out::println);

            System.out.println("Course with ID 1: " + courseService.viewCourse(1L));

            Course updateCourse = new Course(1L, "test1 updated", "Updated description", 1);
            courseService.updateCourse(updateCourse);

            System.out.println("After update: " + courseService.viewCourse(1L));

            courseService.deleteCourse(2L);
            System.out.println("After delete:");
            courseService.viewAllCourses().forEach(System.out::println);
        };
    }
}
