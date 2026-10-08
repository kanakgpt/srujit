package com.intuit.first_project.controller;

import com.intuit.first_project.dto.StudentDTO;
import com.intuit.first_project.response.ApiResponse;
import com.intuit.first_project.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    StudentService studentService;

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<StudentDTO>> saveStudent(@Valid @RequestBody StudentDTO studentDto){
        StudentDTO savedStudent = studentService.saveStudent(studentDto);
        ApiResponse<StudentDTO> response = new ApiResponse<>(
                HttpStatus.CREATED.value(),
                "Student created successfully",
                savedStudent
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentDTO>> getStudentById(@PathVariable long id){
        StudentDTO student = studentService.getStudentById(id);
        ApiResponse<StudentDTO> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Student retrieved successfully",
                student
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentDTO>> updateStudent(
            @PathVariable long id,
            @Valid @RequestBody StudentDTO studentDto) {
        StudentDTO updatedStudent = studentService.updateStudent(id, studentDto);
        ApiResponse<StudentDTO> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Student updated successfully",
                updatedStudent
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{student_id}")
    public ResponseEntity<ApiResponse<Object>> deleteStudent(@PathVariable long student_id) {
        studentService.deleteStudent(student_id);
        ApiResponse<Object> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Student deleted successfully",
                null
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
