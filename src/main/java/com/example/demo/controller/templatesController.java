package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/")
public class templatesController {
    @GetMapping("login")
public String getLoginView()
{
   return "Login";
}

@GetMapping("courses")
    public String getCourses()
{
    return "courses";
}


}

