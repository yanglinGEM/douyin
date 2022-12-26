package com.example.douyin.dto;

import java.time.LocalDateTime;

public class Comments {
    private int id;
    private int user_id;
    private int video_id;
    private String comment_text;
    private LocalDateTime create_time;
    private int cancel;

    public Comments() {
    }

    public Comments(int user_id, int video_id, String comment_text, LocalDateTime create_time, int cancel) {
        this.user_id = user_id;
        this.video_id = video_id;
        this.comment_text = comment_text;
        this.create_time = create_time;
        this.cancel = cancel;
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

    public String getComment_text() {
        return comment_text;
    }

    public void setComment_text(String comment_text) {
        this.comment_text = comment_text;
    }

    public LocalDateTime getCreate_time() {
        return create_time;
    }

    public void setCreate_time(LocalDateTime create_time) {
        this.create_time = create_time;
    }

    public int getCancel() {
        return cancel;
    }

    public void setCancel(int cancel) {
        this.cancel = cancel;
    }
}
