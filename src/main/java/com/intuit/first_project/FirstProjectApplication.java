package com.intuit.first_project;

import com.intuit.first_project.entity.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FirstProjectApplication {

	public static void main(String[] args) {
		Student s=new Student();

		SpringApplication.run(FirstProjectApplication.class, args);
	}

}
