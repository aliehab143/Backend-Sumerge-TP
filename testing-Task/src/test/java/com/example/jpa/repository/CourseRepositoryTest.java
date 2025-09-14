package com.example.jpa.repository;

import com.example.jpa.entity.Author;
import com.example.jpa.entity.Course;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;


import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CourseRepositoryTest {

    private final TestEntityManager entityManager;
    private final CourseRepository courseRepository;

    @Autowired
    CourseRepositoryTest(TestEntityManager entityManager, CourseRepository courseRepository) {
        this.entityManager = entityManager;
        this.courseRepository = courseRepository;
    }

    private Course testCourse;
    private Author testAuthor;

    @BeforeEach
    void setUp() {
        testAuthor = new Author();
        testAuthor.setName("John Doe");
        testAuthor.setEmail("john.doe@example.com");
        testAuthor.setBirthdate(LocalDate.of(1980, 1, 1));
        testAuthor.setCourses(new HashSet<>());

        testCourse = new Course();
        testCourse.setName("Java Programming");
        testCourse.setDescription("Learn Java basics");
        testCourse.setCredit(3);
        
        Set<Author> authors = new HashSet<>();
        authors.add(testAuthor);
        testCourse.setAuthors(authors);
        
        testAuthor.getCourses().add(testCourse);

        entityManager.persist(testAuthor);
        entityManager.persist(testCourse);
        entityManager.flush();
    }

    @Test
    void save_ShouldPersistCourse() {

        Course newCourse = new Course();
        newCourse.setName("Spring Boot");
        newCourse.setDescription("Learn Spring Boot");
        newCourse.setCredit(4);

        Course savedCourse = courseRepository.save(newCourse);

        assertThat(savedCourse).isNotNull();
        assertThat(savedCourse.getId()).isNotNull();
        assertThat(savedCourse.getName()).isEqualTo("Spring Boot");
        assertThat(savedCourse.getCredit()).isEqualTo(4);
    }

    @Test
    void findById_WhenCourseExists_ShouldReturnCourse() {
        Optional<Course> foundCourse = courseRepository.findById(testCourse.getId());

        assertThat(foundCourse).isPresent();
        assertThat(foundCourse.get().getName()).isEqualTo("Java Programming");
        assertThat(foundCourse.get().getCredit()).isEqualTo(3);
    }

    @Test
    void findById_WhenCourseDoesNotExist_ShouldReturnEmpty() {
        Optional<Course> foundCourse = courseRepository.findById(999L);

        assertThat(foundCourse).isEmpty();
    }

    @Test
    void findAll_ShouldReturnAllCourses() {
        var courses = courseRepository.findAll();

        assertThat(courses).isNotEmpty();
        assertThat(courses).hasSize(1);
        assertThat(courses.get(0).getName()).isEqualTo("Java Programming");
    }

    @Test
    void count_ShouldReturnCorrectCount() {
        long count = courseRepository.count();

        assertThat(count).isEqualTo(1);
    }
}
