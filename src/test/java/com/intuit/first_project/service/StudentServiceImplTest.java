package com.intuit.first_project.service;

import com.intuit.first_project.dto.StudentDTO;
import com.intuit.first_project.entity.Student;
import com.intuit.first_project.exception.StudentNotFoundException;
import com.intuit.first_project.repo.StudentRepo;
import com.intuit.first_project.service.impl.StudenetServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentServiceImplTest {

    private StudentRepo studentRepo;
    private StudenetServiceImpl studentService;

    @BeforeEach
    void setUp() {
        studentRepo = mock(StudentRepo.class);
        studentService = new StudenetServiceImpl();
        studentService.studentRepo = studentRepo;
    }

    @Test
    void saveStudent_shouldReturnSavedStudent() {
        StudentDTO request = new StudentDTO();
        request.setFirstName("John");
        request.setLastName("Doe");
        request.setEmail("john.doe@example.com");
        request.setAddress("123 Main St");
        request.setAge(25);

        Student savedStudent = new Student();
        savedStudent.setStudentId(1L);
        savedStudent.setFirstName("John");
        savedStudent.setLastName("Doe");
        savedStudent.setEmail("john.doe@example.com");
        savedStudent.setAddress("123 Main St");
        savedStudent.setAge(25);

        when(studentRepo.save(any(Student.class))).thenReturn(savedStudent);

        StudentDTO response = studentService.saveStudent(request);

        assertNotNull(response);
        assertEquals(1L, response.getStudentId());
        assertEquals("John", response.getFirstName());
        assertEquals("Doe", response.getLastName());
        verify(studentRepo, times(1)).save(any(Student.class));
    }

    @Test
    void getStudentById_shouldReturnStudentWhenFound() {
        Student student = new Student();
        student.setStudentId(2L);
        student.setFirstName("Alice");
        student.setLastName("Smith");
        student.setEmail("alice@example.com");
        student.setAddress("456 Elm St");
        student.setAge(30);

        when(studentRepo.findById(2L)).thenReturn(Optional.of(student));

        StudentDTO response = studentService.getStudentById(2L);

        assertNotNull(response);
        assertEquals(2L, response.getStudentId());
        assertEquals("Alice", response.getFirstName());
        assertEquals("Smith", response.getLastName());
    }

    @Test
    void getStudentById_shouldThrowExceptionWhenNotFound() {
        when(studentRepo.findById(99L)).thenReturn(Optional.empty());

        StudentNotFoundException exception = assertThrows(StudentNotFoundException.class,
                () -> studentService.getStudentById(99L));

        assertTrue(exception.getMessage().contains("99"));
    }

    @Test
    void updateStudent_shouldUpdateExistingStudent() {
        Student existing = new Student();
        existing.setStudentId(10L);
        existing.setFirstName("Old");
        existing.setLastName("Name");
        existing.setEmail("old@example.com");
        existing.setAddress("Old Address");
        existing.setAge(20);

        StudentDTO updateRequest = new StudentDTO();
        updateRequest.setFirstName("New");
        updateRequest.setLastName("Updated");
        updateRequest.setEmail("new@example.com");
        updateRequest.setAddress("New Address");
        updateRequest.setAge(28);

        when(studentRepo.findById(10L)).thenReturn(Optional.of(existing));
        when(studentRepo.save(existing)).thenReturn(existing);

        StudentDTO response = studentService.updateStudent(10L, updateRequest);

        assertNotNull(response);
        assertEquals("New", response.getFirstName());
        assertEquals("Updated", response.getLastName());
        assertEquals("new@example.com", response.getEmail());
        assertEquals("New Address", response.getAddress());
        assertEquals(28, response.getAge());
        verify(studentRepo).save(existing);
    }

    @Test
    void deleteStudent_shouldDeleteWhenStudentExists() {
        Student student = new Student();
        student.setStudentId(5L);

        when(studentRepo.findById(5L)).thenReturn(Optional.of(student));

        assertDoesNotThrow(() -> studentService.deleteStudent(5L));
        verify(studentRepo).deleteById(5L);
    }

    @Test
    void deleteStudent_shouldThrowExceptionWhenStudentDoesNotExist() {
        when(studentRepo.findById(7L)).thenReturn(Optional.empty());

        assertThrows(StudentNotFoundException.class, () -> studentService.deleteStudent(7L));
    }

    @Test
    void getAllStudents_shouldReturnListOfStudents() {
        Student student1 = new Student();
        student1.setStudentId(1L);
        student1.setFirstName("A");
        student1.setLastName("A");
        student1.setEmail("a@test.com");
        student1.setAddress("Add 1");
        student1.setAge(22);

        Student student2 = new Student();
        student2.setStudentId(2L);
        student2.setFirstName("B");
        student2.setLastName("B");
        student2.setEmail("b@test.com");
        student2.setAddress("Add 2");
        student2.setAge(24);

        when(studentRepo.findAll()).thenReturn(List.of(student1, student2));

        List<StudentDTO> students = studentService.getAllStudnets();

        assertEquals(2, students.size());
        assertEquals("A", students.get(0).getFirstName());
        assertEquals("B", students.get(1).getFirstName());
    }
}

