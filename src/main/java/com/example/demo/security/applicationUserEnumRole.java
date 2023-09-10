package com.example.demo.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum applicationUserEnumRole
{
STUDENT(Sets.newHashSet(applicationUserPermissions.STUDENT_READ,applicationUserPermissions.STUDENT_WRITE,applicationUserPermissions.COURSE_READ)),
    ADMIN(Sets.newHashSet(applicationUserPermissions.STUDENT_READ,applicationUserPermissions.STUDENT_WRITE,applicationUserPermissions.COURSE_WRITE,applicationUserPermissions.COURSE_READ)),
    ADMINTRAINEE(Sets.newHashSet(applicationUserPermissions.COURSE_READ,applicationUserPermissions.STUDENT_READ));


private final Set<applicationUserPermissions> permissions;

    applicationUserEnumRole(Set<applicationUserPermissions> permissions) {
        this.permissions = permissions;
    }

    public Set<applicationUserPermissions> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGuranteedAuthorties()
    {
        Set<SimpleGrantedAuthority> permissions =
                getPermissions()
                        .stream()
                        .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                        .collect(Collectors.toSet());

        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

        return permissions;

    }

}
