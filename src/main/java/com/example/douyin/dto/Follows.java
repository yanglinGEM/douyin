package com.example.douyin.dto;

public class Follows {
    private int id;
    private int user_id;
    private int follower_id;
    private int cancel;

    public Follows() {
    }

    public Follows(int user_id, int follower_id, int cancel) {
        this.user_id = user_id;
        this.follower_id = follower_id;
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

    public int getFollower_id() {
        return follower_id;
    }

    public void setFollower_id(int follower_id) {
        this.follower_id = follower_id;
    }

    public int getCancel() {
        return cancel;
    }

    public void setCancel(int cancel) {
        this.cancel = cancel;
    }
}
