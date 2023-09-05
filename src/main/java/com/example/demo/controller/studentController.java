package com.example.demo.controller;


import com.example.demo.model.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/students")
public class studentController
{

    private static final List<Student> students = Arrays.asList(
            new Student(1,"James Bond"),
            new Student(2,"Bhimesh"),
            new Student(3,"Charan"),
            new Student(4,"Chinni"),
            new Student(5,"Dihith"));

    @GetMapping(path = "/{studentId}")
    public Student getStudent(@PathVariable("studentId") Integer studentId)
    {
       return students.stream().
               filter(student -> studentId.equals(student.getStudentId()))
               .findFirst()
               .orElseThrow(() -> new IllegalStateException("student " + studentId + " does not exist"));
    }


}
