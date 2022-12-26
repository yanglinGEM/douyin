package com.example.douyin.dto;

import java.time.LocalDateTime;

public class Comment {
    private int id;
    private int user_id;
    private int video_id;
    private String content;
    private LocalDateTime create_date;
    private int cancel;

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Comment() {
    }

    public Comment(int user_id, int video_id, String comment_text, LocalDateTime create_time, int cancel) {
        this.user_id = user_id;
        this.video_id = video_id;
        this.content = comment_text;
        this.create_date = create_time;
        this.cancel = cancel;
    }

    public Comment(String content, LocalDateTime create_date, User user) {
        this.content = content;
        this.create_date = create_date;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getVideo_id() {
        return video_id;
    }

    public void setVideo_id(int video_id) {
        this.video_id = video_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreate_date() {
        return create_date;
    }

    public void setCreate_date(LocalDateTime create_date) {
        this.create_date = create_date;
    }

    public int getCancel() {
        return cancel;
    }

    public void setCancel(int cancel) {
        this.cancel = cancel;
    }
}
