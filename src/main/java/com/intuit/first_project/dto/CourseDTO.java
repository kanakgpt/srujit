package com.intuit.first_project.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class CourseDTO {

    private String courseId;

    private String courseName;
    
}
