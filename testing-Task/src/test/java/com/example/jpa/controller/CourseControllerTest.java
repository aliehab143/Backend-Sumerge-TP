package com.example.jpa.controller;

import com.example.jpa.dto.CourseDTO;
import com.example.jpa.service.CourseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CourseController.class)
class CourseControllerTest {

    private final MockMvc mockMvc;

    @MockBean
    private CourseService courseService;

    private  final ObjectMapper objectMapper;

    private CourseDTO testCourseDTO;
    @Autowired
    CourseControllerTest(MockMvc mockMvc,  ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }


   

    @BeforeEach
    void setUp() {
        testCourseDTO = new CourseDTO();
        testCourseDTO.setId(1L);
        testCourseDTO.setName("Test Course");
        testCourseDTO.setDescription("Test Description");
        testCourseDTO.setCredit(3);
    }

    @Test
    void findAllCourses_ShouldReturnPageOfCourses() throws Exception {
        Page<CourseDTO> coursePage = new PageImpl<>(Arrays.asList(testCourseDTO));
        when(courseService.findAll(0, 5)).thenReturn(coursePage);

        
        mockMvc.perform(get("/courses")
                        .param("page", "0")
                        .param("size", "5")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value(1))
                .andExpect(jsonPath("$.content[0].name").value("Test Course"))
                .andExpect(jsonPath("$.content[0].description").value("Test Description"))
                .andExpect(jsonPath("$.content[0].credit").value(3));
    }

    @Test
    void findAllCourses_WithDefaultParams_ShouldUseDefaultValues() throws Exception {

        Page<CourseDTO> coursePage = new PageImpl<>(Arrays.asList(testCourseDTO));
        when(courseService.findAll(0, 5)).thenReturn(coursePage);

        mockMvc.perform(get("/courses")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name").value("Test Course"));
    }

    @Test
    void findCourseById_WhenCourseExists_ShouldReturnCourse() throws Exception {
       
        when(courseService.viewCourse(1L)).thenReturn(testCourseDTO);

        mockMvc.perform(get("/courses/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Test Course"))
                .andExpect(jsonPath("$.description").value("Test Description"))
                .andExpect(jsonPath("$.credit").value(3));
    }

    @Test
    void findCourseById_WhenCourseNotExists_ShouldThrowException() throws Exception {

        when(courseService.viewCourse(999L)).thenThrow(new RuntimeException("Course not found with ID: 999"));

        
        mockMvc.perform(get("/courses/999")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Error: Course not found with ID: 999"));
    }

    @Test
    void saveCourse_ShouldCreateNewCourse() throws Exception {

        CourseDTO newCourseDTO = new CourseDTO();
        newCourseDTO.setName("New Course");
        newCourseDTO.setDescription("New Description");
        newCourseDTO.setCredit(4);

        CourseDTO savedCourseDTO = new CourseDTO();
        savedCourseDTO.setId(2L);
        savedCourseDTO.setName("New Course");
        savedCourseDTO.setDescription("New Description");
        savedCourseDTO.setCredit(4);

        when(courseService.addCourse(any(CourseDTO.class))).thenReturn(savedCourseDTO);

        mockMvc.perform(post("/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newCourseDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.name").value("New Course"))
                .andExpect(jsonPath("$.description").value("New Description"))
                .andExpect(jsonPath("$.credit").value(4));
    }

    @Test
    void updateCourse_ShouldUpdateExistingCourse() throws Exception {
        CourseDTO updateDTO = new CourseDTO();
        updateDTO.setName("Updated Course");
        updateDTO.setDescription("Updated Description");
        updateDTO.setCredit(5);

        CourseDTO updatedCourseDTO = new CourseDTO();
        updatedCourseDTO.setId(1L);
        updatedCourseDTO.setName("Updated Course");
        updatedCourseDTO.setDescription("Updated Description");
        updatedCourseDTO.setCredit(5);

        when(courseService.updateCourse(any(Long.class), any(CourseDTO.class))).thenReturn(updatedCourseDTO);

        mockMvc.perform(put("/courses/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Updated Course"))
                .andExpect(jsonPath("$.description").value("Updated Description"))
                .andExpect(jsonPath("$.credit").value(5));
    }

    @Test
    void deleteCourse_ShouldDeleteCourse() throws Exception {
        doNothing().when(courseService).deleteCourse(1L);

        mockMvc.perform(delete("/courses/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getCoursesByAuthorEmail_ShouldReturnCoursesForAuthor() throws Exception {
        String email = "author@example.com";
        Page<CourseDTO> coursePage = new PageImpl<>(Arrays.asList(testCourseDTO));
        when(courseService.getCoursesByAuthorEmail(any(), any())).thenReturn(coursePage);

        mockMvc.perform(get("/courses/author")
                        .param("email", email)
                        .param("page", "0")
                        .param("size", "5")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value(1))
                .andExpect(jsonPath("$.content[0].name").value("Test Course"));
    }

    @Test
    void getCoursesByAuthorEmail_WithDefaultParams_ShouldUseDefaults() throws Exception {
        String email = "author@example.com";
        Page<CourseDTO> coursePage = new PageImpl<>(Arrays.asList(testCourseDTO));
        when(courseService.getCoursesByAuthorEmail(any(), any())).thenReturn(coursePage);

        mockMvc.perform(get("/courses/author")
                        .param("email", email)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name").value("Test Course"));
    }
}
