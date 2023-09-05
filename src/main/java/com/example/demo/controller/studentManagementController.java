package com.example.demo.controller;


import com.example.demo.model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/api/v1/students")
public class studentManagementController
{
    private  static final List<Student> STUDENTS  = new ArrayList<>(Arrays.asList(

            new Student(1,"Sowmya"),
            new Student(2,"Ramya"),
            new Student(3,"Ashik"),
            new Student(4,"Tarun"),
            new Student(5,"Ashraf"),
            new Student(6, "John"),
        new Student(7, "Jane"),
        new Student(8, "Michael"),
        new Student(9, "Emily"),
        new Student(10, "David")));


    @GetMapping
    public List<Student> getAllStudents ()
    {
        return STUDENTS;
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Student student)
    {
        System.out.println(student);
        STUDENTS.add(student);
        System.out.println("Student inserted" + student.toString());

    }

    @DeleteMapping( path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Integer studentId)
    {

        System.out.println(studentId);
        if(studentId>=0 && studentId< STUDENTS.size()) {
            STUDENTS.remove(studentId.intValue());
            System.out.println("Student deleted" );
        }
    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(@PathVariable("studentId") Integer studentId , @RequestBody Student student)
    {
        System.out.println(String.format("%s %s",studentId,student));

        if(studentId>=0 && studentId< STUDENTS.size()) {
            STUDENTS.set(studentId,student);
            System.out.println("Student updated" + student.toString());
        }

    }


}
