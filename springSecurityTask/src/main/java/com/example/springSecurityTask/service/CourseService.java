package com.example.springSecurityTask.service;

import com.example.springSecurityTask.dto.CourseDTO;
import com.example.springSecurityTask.entity.Course;
import com.example.springSecurityTask.mapper.CourseMapper;
import com.example.springSecurityTask.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public Page<CourseDTO> findAll(int page , int size ) {
        return courseRepository.findAll(PageRequest.of(page, size))
                .map(courseMapper::toDTO);
    }

    public CourseDTO viewCourse(Long id) {
        return courseRepository.findById(id)
                .map(courseMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Course not found with ID: " + id));
    }

    public CourseDTO addCourse(CourseDTO courseDTO) {
        Course course = courseMapper.toEntity(courseDTO);
        Course savedCourse = courseRepository.save(course);
        return courseMapper.toDTO(savedCourse);
    }

    public CourseDTO updateCourse(Long id, CourseDTO courseDTO) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cannot update - Course not found with ID: " + id));
        course.setName(courseDTO.getName());
        // you might also want to update description/credit/etc.
        courseRepository.save(course);
        return courseMapper.toDTO(course);
    }

    public void deleteCourse(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cannot delete - Course not found with ID: " + id));
        courseRepository.delete(course);
    }

    public Page<CourseDTO> getCoursesByAuthorEmail(String email, Pageable pageable) {
        return courseRepository.findByAuthorsEmail(email, pageable)
                .map(courseMapper::toDTO);
    }
}
