package com.example.jpa.mapper;

import com.example.jpa.dto.CourseDTO;
import com.example.jpa.entity.Author;
import com.example.jpa.entity.Course;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CourseMapperTest {

    @Autowired
    private CourseMapper courseMapper;

    @Test
    void toDTO_ShouldMapCourseToDTO() {
        Course course = new Course();
        course.setId(1L);
        course.setName("Java Fundamentals");
        course.setDescription("Learn Java basics");
        course.setCredit(3);

        Author author = new Author();
        author.setId(1L);
        author.setName("John Doe");
        author.setEmail("john@example.com");
        author.setBirthdate(LocalDate.of(1980, 1, 1));

        Set<Author> authors = new HashSet<>();
        authors.add(author);
        course.setAuthors(authors);

       
        CourseDTO result = courseMapper.toDTO(course);

       
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getName()).isEqualTo("Java Fundamentals");
        assertThat(result.getDescription()).isEqualTo("Learn Java basics");
        assertThat(result.getCredit()).isEqualTo(3);
        assertThat(result.getAuthorNames()).containsExactly("John Doe");
    }

    @Test
    void toDTO_WithNullAuthors_ShouldReturnEmptyList() {
        Course course = new Course();
        course.setId(1L);
        course.setName("Test Course");
        course.setAuthors(null);

        CourseDTO result = courseMapper.toDTO(course);

        assertThat(result).isNotNull();
        assertThat(result.getAuthorNames()).isEmpty();
    }

    @Test
    void toEntity_ShouldMapDTOToCourse() {
        CourseDTO dto = new CourseDTO();
        dto.setName("Spring Boot Course");
        dto.setDescription("Advanced Spring Boot");
        dto.setCredit(4);
        dto.setAuthorNames(List.of("Jane Smith"));

        Course result = courseMapper.toEntity(dto);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isNull(); // Should be ignored
        assertThat(result.getName()).isEqualTo("Spring Boot Course");
        assertThat(result.getDescription()).isEqualTo("Advanced Spring Boot");
        assertThat(result.getCredit()).isEqualTo(4);
        assertThat(result.getAuthors()).isNull(); // Should be ignored
        assertThat(result.getAssessments()).isNull(); // Should be ignored
        assertThat(result.getRatings()).isNull(); // Should be ignored
    }
}
