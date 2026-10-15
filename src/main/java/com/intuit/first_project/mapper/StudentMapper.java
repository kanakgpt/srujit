package com.intuit.first_project.mapper;

import com.intuit.first_project.dto.StudentDTO;
import com.intuit.first_project.entity.Student;

public class StudentMapper {
    public static Student toEntity(StudentDTO studentDTO) {
        Student student = new Student();
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setStudentId(studentDTO.getStudentId());
        student.setEmail(studentDTO.getEmail());
        student.setAddress(studentDTO.getAddress());
        student.setAge(studentDTO.getAge());
        if(studentDTO.getCourses() != null&&!studentDTO.getCourses().isEmpty()) {
            student.setCourses(studentDTO.getCourses().stream()
                    .map(courseDTO -> {
                        var course = new com.intuit.first_project.entity.Course();
                        course.setCourseId(courseDTO.getCourseId());
                        course.setCourseName(courseDTO.getCourseName());
                        return course;
                    })
                    .collect(java.util.stream.Collectors.toSet()));
        }
        return student;
    }

    public static StudentDTO toDTO(Student student){
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setFirstName(student.getFirstName());
        studentDTO.setLastName(student.getLastName());
        studentDTO.setEmail(student.getEmail());
        studentDTO.setAddress(student.getAddress());
        studentDTO.setAge(student.getAge());
        studentDTO.setStudentId(student.getStudentId());
        return studentDTO;

    }

}
