package com.example.RestController.controller;

import com.example.RestController.model.Course;
import com.example.RestController.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() { // for the db courses
        List<Course> courses = courseService.viewAllCourses();
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        Course course = courseService.getCourseById(id);
        if (course != null) {
            return ResponseEntity.ok(course);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<String> addCourse(@RequestBody Course course) {
        int result = courseService.addCourse(course);
        if (result > 0) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Course added successfully!");
        }
        return ResponseEntity.badRequest().body("Failed to add course");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCourse(@PathVariable Long id, @RequestBody Course course) {
        course.setId(id);
        int result = courseService.updateCourse(course);
        if (result > 0) {
            return ResponseEntity.ok("Course updated successfully!");
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long id) {
        int result = courseService.deleteCourse(id);
        if (result > 0) {
            return ResponseEntity.ok("Course deleted successfully!");
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/discover") // this is for the bean exits
    public ResponseEntity<List<Course>> discoverAllCourses() {
        List<Course> recommendedCourses = courseService.discoverAllCourses();
        return ResponseEntity.ok(recommendedCourses);
    }
}
