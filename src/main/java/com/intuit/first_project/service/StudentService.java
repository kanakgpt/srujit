package com.intuit.first_project.service;

import com.intuit.first_project.dto.StudentDTO;
import org.springframework.stereotype.Service;


public interface StudentService {

    StudentDTO saveStudent(StudentDTO studentDTO);

    StudentDTO getStudentById(long id);
}
