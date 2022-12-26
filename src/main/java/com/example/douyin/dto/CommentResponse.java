package com.example.douyin.dto;

import java.util.List;

public class CommentResponse {
    private int status_code;
    private String status_msg;
    private Comment comment;
    private List<Comment> comment_list;

    public List<Comment> getComment_list() {
        return comment_list;
    }

    public void setComment_list(List<Comment> comment_list) {
        this.comment_list = comment_list;
    }

    public CommentResponse(int status_code, String status_msg, List<Comment> comment_list) {
        this.status_code = status_code;
        this.status_msg = status_msg;
        this.comment_list = comment_list;
    }

    public CommentResponse(int status_code, String status_msg, Comment comment) {
        this.status_code = status_code;
        this.status_msg = status_msg;
        this.comment = comment;
    }

    public CommentResponse() {
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

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }
}
