package com.intuit.first_project.service.impl;

import com.intuit.first_project.dto.StudentDTO;
import com.intuit.first_project.entity.Student;
import com.intuit.first_project.exception.StudentNotFoundException;
import com.intuit.first_project.mapper.StudentMapper;
import com.intuit.first_project.repo.StudentRepo;
import com.intuit.first_project.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudenetServiceImpl implements StudentService {
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    public StudentRepo studentRepo;

    @Override
    public StudentDTO saveStudent(StudentDTO studentDTO) {
        logger.info("Inside saveStudent");
       Student student= StudentMapper.toEntity(studentDTO);
       logger.info("Student entity created from DTO");
        return StudentMapper.toDTO(studentRepo.save(student));
    }

    @Override
    public StudentDTO getStudentById(long id) {
      logger.info("Inside getStudentById");
      Optional<Student> student=  studentRepo.findById(id);
      if(!student.isPresent()){
          logger.error("Student not found with id: " + id);
          throw new StudentNotFoundException("Student not found with id: " + id);
      }
      StudentDTO studentDTO=StudentMapper.toDTO(student.get());
      logger.info("Student found with id: " + id);
      return studentDTO;
     }

    @Override
    public StudentDTO updateStudent(long id, StudentDTO studentDTO) {
        Optional<Student> existingStudent = studentRepo.findById(id);
        if (!existingStudent.isPresent()) {
            logger.error("Student not found with id: " + id);
            throw new StudentNotFoundException("Student not found with id: " + id);
        }
        logger.info("Student found with id: " + id);

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

    @Override
    public List<StudentDTO> getAllStudnets() {
        System.out.println("in service getAllStudnets");
      List<Student> students=  studentRepo.findAll();
      List<StudentDTO> list=new ArrayList<>();
      for(Student student:students){
          list.add(StudentMapper.toDTO(student));
      }
      System.out.println("database query executed");
      return  list;
      //  return students.stream().map(StudentMapper::toDTO).toList();

    }

}
