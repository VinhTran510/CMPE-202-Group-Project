package com.example.cmpe202project;

import com.example.cmpe202project.model.Student;
import com.example.cmpe202project.service.DatabaseConnectionService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@SpringBootApplication
public class Cmpe202projectApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Cmpe202projectApplication.class, args);
		DatabaseConnectionService databaseConnectionService = context.getBean(DatabaseConnectionService.class);
		List<Student> students = databaseConnectionService.checkConnection();
		students.forEach(System.out::println);
	}
}