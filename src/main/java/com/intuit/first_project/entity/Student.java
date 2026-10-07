package com.intuit.first_project.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name="students")
public class Student {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long studentId;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email")
    private String email;

    @Column(name="address")
    private String address;

    @Column(name="age")
    private Integer age;

    @Column(name="dob")
    private Date dob;


}
