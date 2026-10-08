package com.intuit.first_project.service.impl;

import com.intuit.first_project.dto.StudentDTO;
import com.intuit.first_project.entity.Student;
import com.intuit.first_project.exception.StudentNotFoundException;
import com.intuit.first_project.mapper.StudentMapper;
import com.intuit.first_project.repo.StudentRepo;
import com.intuit.first_project.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudenetServiceImpl implements StudentService {
    @Autowired
    private StudentRepo studentRepo;

    @Override
    public StudentDTO saveStudent(StudentDTO studentDTO) {
       Student student= StudentMapper.toEntity(studentDTO);
        return StudentMapper.toDTO(studentRepo.save(student));
    }

    @Override
    public StudentDTO getStudentById(long id) {
      Optional<Student> student=  studentRepo.findById(id);
      if(!student.isPresent()){
          throw new StudentNotFoundException("Student not found with id: " + id);
      }
      StudentDTO studentDTO=StudentMapper.toDTO(student.get());
      return studentDTO;
     }

    @Override
    public StudentDTO updateStudent(long id, StudentDTO studentDTO) {
        Optional<Student> existingStudent = studentRepo.findById(id);
        if (!existingStudent.isPresent()) {
            throw new StudentNotFoundException("Student not found with id: " + id);
        }

        Student student = existingStudent.get();

        if (studentDTO.getFirstName() != null) {
            student.setFirstName(studentDTO.getFirstName());
        }
        if (studentDTO.getLastName() != null) {
            student.setLastName(studentDTO.getLastName());
        }
        if (studentDTO.getEmail() != null) {
            student.setEmail(studentDTO.getEmail());
        }
        if (studentDTO.getAddress() != null) {
            student.setAddress(studentDTO.getAddress());
        }
        if (studentDTO.getAge() != null) {
            student.setAge(studentDTO.getAge());
        }
        if (studentDTO.getDob() != null) {
            student.setDob(studentDTO.getDob());
        }

        Student updatedStudent = studentRepo.save(student);
        return StudentMapper.toDTO(updatedStudent);
    }

    @Override
    public void deleteStudent(long id) {
        Optional<Student> student = studentRepo.findById(id);
        if (!student.isPresent()) {
            throw new StudentNotFoundException("Student not found with id: " + id);
        }
        studentRepo.deleteById(id);
    }

}
