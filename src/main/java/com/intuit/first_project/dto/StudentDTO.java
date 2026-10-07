package com.intuit.first_project.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.util.Date;
@Data
public class StudentDTO {

    private long studentId;

    private String firstName;

    private String lastName;

    private String email;

    private String address;

    private Integer age;

    private Date dob;

}
