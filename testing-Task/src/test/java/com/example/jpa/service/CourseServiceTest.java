package com.example.jpa.service;

import com.example.jpa.dto.CourseDTO;
import com.example.jpa.entity.Course;
import com.example.jpa.mapper.CourseMapper;
import com.example.jpa.repository.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CourseServiceTest {

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private CourseMapper courseMapper;

    @InjectMocks
    private CourseService courseService;

    private Course testCourse;
    private CourseDTO testCourseDTO;
    private PageRequest pageRequest;

    @BeforeEach
    void setUp() {
        testCourse = new Course();
        testCourse.setId(1L);
        testCourse.setName("Test Course");
        testCourse.setDescription("Test Description");
        testCourse.setCredit(3);

        testCourseDTO = new CourseDTO();
        testCourseDTO.setId(1L);
        testCourseDTO.setName("Test Course");
        testCourseDTO.setDescription("Test Description");
        testCourseDTO.setCredit(3);
        pageRequest = PageRequest.of(0, 5);
    }

    @Test
    void findAll_ShouldReturnPageOfCourseDTOs() {

        Page<Course> coursePage = new PageImpl<>(Arrays.asList(testCourse));
        when(courseRepository.findAll(pageRequest)).thenReturn(coursePage);
        when(courseMapper.toDTO(testCourse)).thenReturn(testCourseDTO);

        Page<CourseDTO> result = courseService.findAll(0, 5);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(testCourseDTO.getName(), result.getContent().get(0).getName());
        verify(courseRepository).findAll(pageRequest);
        verify(courseMapper).toDTO(testCourse);
    }

    @Test
    void viewCourse_WhenCourseExists_ShouldReturnCourseDTO() {
        Long courseId = 1L;
        when(courseRepository.findById(courseId)).thenReturn(Optional.of(testCourse));
        when(courseMapper.toDTO(testCourse)).thenReturn(testCourseDTO);

        CourseDTO result = courseService.viewCourse(courseId);

        assertNotNull(result);
        assertEquals(testCourseDTO.getName(), result.getName());
        verify(courseRepository).findById(courseId);
        verify(courseMapper).toDTO(testCourse);
    }

    @Test
    void viewCourse_WhenCourseNotExists_ShouldThrowRuntimeException() {
     
        Long courseId = 999L;
        when(courseRepository.findById(courseId)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, 
            () -> courseService.viewCourse(courseId));
        
        assertEquals("Course not found with ID: 999", exception.getMessage());
        verify(courseRepository).findById(courseId);
        verify(courseMapper, never()).toDTO(any());
    }

    @Test
    void addCourse_ShouldSaveCourseAndReturnDTO() {
        Course newCourse = new Course();
        newCourse.setName("New Course");
        
        Course savedCourse = new Course();
        savedCourse.setId(2L);
        savedCourse.setName("New Course");

        CourseDTO savedCourseDTO = new CourseDTO();
        savedCourseDTO.setId(2L);
        savedCourseDTO.setName("New Course");

        when(courseMapper.toEntity(testCourseDTO)).thenReturn(newCourse);
        when(courseRepository.save(newCourse)).thenReturn(savedCourse);
        when(courseMapper.toDTO(savedCourse)).thenReturn(savedCourseDTO);

       
        CourseDTO result = courseService.addCourse(testCourseDTO);

        
        assertNotNull(result);
        assertEquals(2L, result.getId());
        assertEquals("New Course", result.getName());
        verify(courseMapper).toEntity(testCourseDTO);
        verify(courseRepository).save(newCourse);
        verify(courseMapper).toDTO(savedCourse);
    }

    @Test
    void updateCourse_WhenCourseExists_ShouldUpdateAndReturnDTO() {
        Long courseId = 1L;
        CourseDTO updateDTO = new CourseDTO();
        updateDTO.setName("Updated Course");
        updateDTO.setDescription("Updated Description");
        updateDTO.setCredit(4);

        when(courseRepository.findById(courseId)).thenReturn(Optional.of(testCourse));
        when(courseRepository.save(testCourse)).thenReturn(testCourse);
        when(courseMapper.toDTO(testCourse)).thenReturn(testCourseDTO);

        CourseDTO result = courseService.updateCourse(courseId, updateDTO);

        assertNotNull(result);
        assertEquals("Updated Course", testCourse.getName()); 
        verify(courseRepository).findById(courseId);
        verify(courseRepository).save(testCourse);
        verify(courseMapper).toDTO(testCourse);
    }

    @Test
    void updateCourse_WhenCourseNotExists_ShouldThrowRuntimeException() {

        Long courseId = 999L;
        when(courseRepository.findById(courseId)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
            () -> courseService.updateCourse(courseId, testCourseDTO));

        assertEquals("Cannot update - Course not found with ID: 999", exception.getMessage());
        verify(courseRepository).findById(courseId);
        verify(courseRepository, never()).save(any());
        verify(courseMapper, never()).toDTO(any());
    }

    @Test
    void deleteCourse_WhenCourseExists_ShouldDeleteCourse() {
        Long courseId = 1L;
        when(courseRepository.findById(courseId)).thenReturn(Optional.of(testCourse));

        courseService.deleteCourse(courseId);

        verify(courseRepository).findById(courseId);
        verify(courseRepository).delete(testCourse);
    }

    @Test
    void deleteCourse_WhenCourseNotExists_ShouldThrowRuntimeException() {
        Long courseId = 999L;
        when(courseRepository.findById(courseId)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
            () -> courseService.deleteCourse(courseId));

        assertEquals("Cannot delete - Course not found with ID: 999", exception.getMessage());
        verify(courseRepository).findById(courseId);
        verify(courseRepository, never()).delete(any());
    }

    @Test
    void getCoursesByAuthorEmail_ShouldReturnPageOfCourseDTOs() {
      
        String email = "author@example.com";
        Pageable pageable = PageRequest.of(0, 5);
        Page<Course> coursePage = new PageImpl<>(Arrays.asList(testCourse));
        
        when(courseRepository.findByAuthorsEmail(email, pageable)).thenReturn(coursePage);
        when(courseMapper.toDTO(testCourse)).thenReturn(testCourseDTO);

        Page<CourseDTO> result = courseService.getCoursesByAuthorEmail(email, pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(testCourseDTO.getName(), result.getContent().get(0).getName());
        verify(courseRepository).findByAuthorsEmail(email, pageable);
        verify(courseMapper).toDTO(testCourse);
    }
}
