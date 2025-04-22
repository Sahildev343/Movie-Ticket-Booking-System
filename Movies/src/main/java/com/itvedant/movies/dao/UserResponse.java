package com.itvedant.movies.dao;

public class UserResponse {
    private Integer userId;
    private String email;

    // Constructor
    public UserResponse(Integer userId, String email) {
        this.userId = userId;
        this.email = email;
    }

    // Getters and Setters
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

