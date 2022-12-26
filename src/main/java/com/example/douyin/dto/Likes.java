package com.example.douyin.dto;

public class Likes {
    private int id;
    private int user_id;
    private int video_id;
    private int cancel;

    public Likes() {
    }

    public Likes(int user_id, int video_id, int cancel) {
        this.user_id = user_id;
        this.video_id = video_id;
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

    public int getCancel() {
        return cancel;
    }

    public void setCancel(int cancel) {
        this.cancel = cancel;
    }
}
