package com.example.douyin.dto;

import java.util.List;

public class UserResponse {
    private int status_code;
    private String status_msg;
    private User user;
    private List<User> user_list;
    public UserResponse(int status_code, String status_msg, User user) {
        this.status_code = status_code;
        this.status_msg = status_msg;
        this.user = user;
    }

    public UserResponse(int status_code, String status_msg, List<User> user_list) {
        this.status_code = status_code;
        this.status_msg = status_msg;
        this.user_list = user_list;
    }

    public List<User> getUser_list() {
        return user_list;
    }

    public void setUser_list(List<User> user_list) {
        this.user_list = user_list;
    }

    public UserResponse() {
    }

    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

    public String getStatus_msg() {
        return status_msg;
    }

    public void setStatus_msg(String status_msg) {
        this.status_msg = status_msg;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
