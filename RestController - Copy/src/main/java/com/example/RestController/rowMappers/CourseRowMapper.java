package com.example.RestController.rowMappers;

import com.example.RestController.model.Course;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseRowMapper implements RowMapper<Course> {
    @Override
    public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
        Course course = new Course();
        course.setId(rs.getLong("id"));
        course.setName(rs.getString("name"));
        course.setDescription(rs.getString("description"));
        course.setCredit(rs.getInt("credit"));
        return course;
    }
}
