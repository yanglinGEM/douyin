package com.example.douyin.controller;


import com.example.douyin.dto.FeedResponse;

import com.example.douyin.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import java.util.Map;

@Controller
public class FeedController {

    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    VideoService videoService;
    @RequestMapping("/douyin/feed")
    @ResponseBody
    public FeedResponse feed(String token) {
        System.out.println("视频流");
        int user_id = 0;
        if (token != null && !token.equals("") && redisTemplate.opsForValue().get(token) != null) user_id = (int) redisTemplate.opsForValue().get(token);
        return new FeedResponse(0,"获取视频流成功",videoService.getVideoList(user_id, -1));
    }

    @RequestMapping("/douyin/publish/action/")
    @ResponseBody
    public Map<String, String> publishAction(String token, MultipartFile data, String title) throws IOException {
        int author_id = -1;
        if (token != null && !token.equals("") && redisTemplate.opsForValue().get(token) != null) author_id = (int) redisTemplate.opsForValue().get(token);
        return videoService.publishVideo(author_id, data, title);
    }

    @RequestMapping("/douyin/publish/list/")
    @ResponseBody
    public FeedResponse publishList(int user_id, String token) {
        System.out.println("发布列表");
        int self_user_id = 0;
        if (token != null && !token.equals("") && redisTemplate.opsForValue().get(token) != null) self_user_id = (int) redisTemplate.opsForValue().get(token);
        return new FeedResponse(0,"获取视频流成功",videoService.getVideoList(self_user_id, user_id));
    }
}
