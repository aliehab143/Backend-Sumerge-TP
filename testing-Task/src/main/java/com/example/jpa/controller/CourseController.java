package com.example.jpa.controller;


import com.example.jpa.dto.CourseDTO;
import com.example.jpa.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;


    @GetMapping
    public Page<CourseDTO> findAllCourses(@RequestParam(defaultValue = "0") int page , @RequestParam (defaultValue = "5")int size)
    {
        return courseService.findAll(page,size);

    }

    @GetMapping("/{id}")
    public CourseDTO findCourseById(@PathVariable long id)
    {
        return courseService.viewCourse(id);
    }
    @PostMapping
    public CourseDTO saveCourse(@RequestBody CourseDTO courseDTO)
    {
        return courseService.addCourse(courseDTO);
    }
    @PutMapping("/{id}")
    public CourseDTO updateCourse(@PathVariable Long id, @RequestBody CourseDTO dto) {
        return courseService.updateCourse(id, dto);
    }
    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Long id)
    {
        courseService.deleteCourse(id);
        System.out.println("Course deleted!");
    }

    @GetMapping("/author")
    public Page<CourseDTO> getCoursesByAuthorEmailPaged(
            @RequestParam String email,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return courseService.getCoursesByAuthorEmail(email, PageRequest.of(page, size));
    }
}
