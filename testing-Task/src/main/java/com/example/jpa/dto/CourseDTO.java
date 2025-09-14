package com.example.jpa.dto;

import lombok.Data;

import java.util.List;

@Data
public class CourseDTO {
    private Long id;
    private String name;
    private String description;
    private Integer credit;
    private List<String> authorNames;
}
