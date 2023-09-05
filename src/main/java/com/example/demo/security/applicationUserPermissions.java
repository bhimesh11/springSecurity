package com.example.demo.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;

public enum applicationUserPermissions
{
    STUDENT_READ("student:read"),
    STUDENT_WRITE("student:write"),

    COURSE_READ("course:read"),
    COURSE_WRITE("course:write");

    private final String permission;


    applicationUserPermissions(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }


}
