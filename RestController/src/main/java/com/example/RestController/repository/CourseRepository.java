package com.example.RestController.repository;

import com.example.RestController.model.Course;
import com.example.RestController.rowMappers.CourseRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CourseRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Course> findAll() {
        String sql = "SELECT * FROM courses";
        return jdbcTemplate.query(sql, new CourseRowMapper());
    }

    public Course findById(Long id) {
        String sql = "SELECT * FROM courses WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new CourseRowMapper(), id);
    }

    public int save(Course course) {
        String sql = "INSERT INTO courses (name, description, credit) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, course.getName(), course.getDescription(), course.getCredit());
    }

    public int update(Course course) {
        String sql = "UPDATE courses SET name = ?, description = ?, credit = ? WHERE id = ?";
        return jdbcTemplate.update(sql, course.getName(), course.getDescription(), course.getCredit(), course.getId());
    }

    public int delete(Long id) {
        String sql = "DELETE FROM courses WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
