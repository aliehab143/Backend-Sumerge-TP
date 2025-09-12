package com.example.RestController.model;

public class Course {
    private Long id;
    private String name;
    private String description;
    private Integer credit;

    public Course() {}

    public Course(Long id, String name, String description, Integer credit) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.credit = credit;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }
}
