package com.example.douyin.dto;

import java.util.List;

public class FeedResponse {
    private int status_code;
    private String status_msg;
    private List<Video> video_list;
    private int next_time;

    public FeedResponse(int status_code, String status_msg, List<Video> video_list) {
        this.status_code = status_code;
        this.status_msg = status_msg;
        this.video_list = video_list;
    }

    public FeedResponse() {
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

    public List<Video> getVideo_list() {
        return video_list;
    }

    public void setVideo_list(List<Video> video_list) {
        this.video_list = video_list;
    }

    public int getNext_time() {
        return next_time;
    }

    public void setNext_time(int next_time) {
        this.next_time = next_time;
    }
}
