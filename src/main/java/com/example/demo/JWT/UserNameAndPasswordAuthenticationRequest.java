package com.example.demo.JWT;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserNameAndPasswordAuthenticationRequest {

    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;

    public UserNameAndPasswordAuthenticationRequest() {
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
