package com.intuit.first_project.service.impl;

import com.intuit.first_project.dto.StudentDTO;
import com.intuit.first_project.entity.Student;
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
          throw new RuntimeException("Student not found "+id);
      }
      StudentDTO studentDTO=StudentMapper.toDTO(student.get());
      return studentDTO;
    }



}
