package com.intuit.first_project.controller;

import com.intuit.first_project.dto.StudentDTO;
import com.intuit.first_project.entity.Student;
import com.intuit.first_project.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    StudentService studentService;

    @PostMapping("/save")
    public StudentDTO saveStudent(@RequestBody StudentDTO studentDto){
        return studentService.saveStudent(studentDto);
    }
    @GetMapping("/{id}")
    public StudentDTO getStudentById(@PathVariable long id){
        return studentService.getStudentById(id);
    }
    //Response Entity (status code,response,developer message)
}
