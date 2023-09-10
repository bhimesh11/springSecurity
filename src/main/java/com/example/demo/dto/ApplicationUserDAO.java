package com.example.demo.dto;

import java.util.Optional;

public interface ApplicationUserDAO
{
    Optional<ApplicationUser> selectApplicationUserBYUserName(String UserName);

}
