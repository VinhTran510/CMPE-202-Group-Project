package com.example.cmpe202project.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import com.example.cmpe202project.model.Student;
import com.example.cmpe202project.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class StringToStudentConverter implements Converter<String, Student> {

    @Autowired
    private StudentService studentService;

    private static final Logger logger = LoggerFactory.getLogger(StringToStudentConverter.class);

    @Override
    public Student convert(String id) {
        try {
            logger.info("Converting id: " + id);
            Student student = studentService.findById(Integer.parseInt(id)).orElse(null);
            logger.info("Converted id to student: " + student);
            return student;
        } catch (Exception e) {
            logger.error("Error converting id to student", e);
            return null;
        }
    }
}