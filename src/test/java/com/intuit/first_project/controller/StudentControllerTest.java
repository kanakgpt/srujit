package com.intuit.first_project.controller;

import com.intuit.first_project.dto.StudentDTO;
import com.intuit.first_project.response.ApiResponse;
import com.intuit.first_project.service.StudentService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentControllerTest {

    private StudentService studentService;
    private StudentController studentController;

    @BeforeEach
    void setUp() {
        studentService = mock(StudentService.class);
        studentController = new StudentController();
        studentController.studentService = studentService;
    }
@AfterEach
    void tearDown() {
        studentService = null;
        studentController = null;
    }
    @Test
    void saveStudent_shouldReturnCreatedResponse() {
        StudentDTO request = new StudentDTO();
        request.setFirstName("John");
        request.setLastName("Doe");
        request.setEmail("john.doe@example.com");
        request.setAddress("123 Main St");
        request.setAge(25);

        StudentDTO savedStudent = new StudentDTO();
        savedStudent.setStudentId(1L);
        savedStudent.setFirstName("John");
        savedStudent.setLastName("Doe");
        savedStudent.setEmail("john.doe@example.com");
        savedStudent.setAddress("123 Main St");
        savedStudent.setAge(25);

        when(studentService.saveStudent(request)).thenReturn(savedStudent);

        ResponseEntity<ApiResponse<StudentDTO>> response = studentController.saveStudent(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Student created successfully", response.getBody().getMessage());
        assertEquals(1L, response.getBody().getData().getStudentId());
        verify(studentService).saveStudent(request);
    }

    @Test
    void getStudentById_shouldReturnStudentResponse() {
        StudentDTO student = new StudentDTO();
        student.setStudentId(2L);
        student.setFirstName("Alice");
        student.setLastName("Smith");
        student.setEmail("alice@example.com");
        student.setAddress("456 Elm St");
        student.setAge(30);

        when(studentService.getStudentById(2L)).thenReturn(student);

        ResponseEntity<ApiResponse<StudentDTO>> response = studentController.getStudentById(2L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Student retrieved successfully", response.getBody().getMessage());
        assertEquals("Alice", response.getBody().getData().getFirstName());
        verify(studentService).getStudentById(2L);
    }

    @Test
    void updateStudent_shouldReturnUpdatedResponse() {
        StudentDTO request = new StudentDTO();
        request.setFirstName("Updated");
        request.setLastName("User");
        request.setEmail("updated@example.com");
        request.setAddress("Updated address");
        request.setAge(35);

        StudentDTO updatedStudent = new StudentDTO();
        updatedStudent.setStudentId(4L);
        updatedStudent.setFirstName("Updated");
        updatedStudent.setLastName("User");
        updatedStudent.setEmail("updated@example.com");
        updatedStudent.setAddress("Updated address");
        updatedStudent.setAge(35);

        when(studentService.updateStudent(4L, request)).thenReturn(updatedStudent);

        ResponseEntity<ApiResponse<StudentDTO>> response = studentController.updateStudent(4L, request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Student updated successfully", response.getBody().getMessage());
        assertEquals("Updated", response.getBody().getData().getFirstName());
        verify(studentService).updateStudent(4L, request);
    }

    @Test
    void deleteStudent_shouldReturnDeletedResponse() {
        doNothing().when(studentService).deleteStudent(3L);

        ResponseEntity<ApiResponse<Object>> response = studentController.deleteStudent(3L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Student deleted successfully", response.getBody().getMessage());
        verify(studentService).deleteStudent(3L);
    }

    @Test
    void getStudents_shouldReturnAllStudents() {
        StudentDTO student1 = new StudentDTO();
        student1.setStudentId(1L);
        student1.setFirstName("A");
        student1.setLastName("A");
        student1.setEmail("a@test.com");
        student1.setAddress("Add 1");
        student1.setAge(21);

        StudentDTO student2 = new StudentDTO();
        student2.setStudentId(2L);
        student2.setFirstName("B");
        student2.setLastName("B");
        student2.setEmail("b@test.com");
        student2.setAddress("Add 2");
        student2.setAge(22);

        when(studentService.getAllStudnets()).thenReturn(List.of(student1, student2));

        ResponseEntity<ApiResponse<List<StudentDTO>>> response = studentController.getStudents();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Students retrieved successfully", response.getBody().getMessage());
        assertEquals(2, response.getBody().getData().size());
        verify(studentService).getAllStudnets();
    }
}

