package com.example.jpa.mapper;

import com.example.jpa.dto.CourseDTO;
import com.example.jpa.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    @Mapping(target = "authorNames", expression = "java(course.getAuthors() != null ? course.getAuthors().stream().map(author -> author.getName()).toList() : java.util.Collections.emptyList())")
    CourseDTO toDTO(Course course);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "authors", ignore = true)
    @Mapping(target = "assessments", ignore = true)
    @Mapping(target = "ratings", ignore = true)
    Course toEntity(CourseDTO dto);
}
