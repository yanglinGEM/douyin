package com.example.douyin.controller;

import com.example.douyin.dao.LikeMapper;
import com.example.douyin.dao.VideoMapper;
import com.example.douyin.dto.FeedResponse;
import com.example.douyin.service.LikeService;
import com.example.douyin.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class LikeController {
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    LikeService likeService;
    @Autowired
    VideoService videoService;
    @RequestMapping("/douyin/favorite/action/")
    @ResponseBody
    public Map<String,String> likeAction(String token, int video_id, int action_type) {
        System.out.println("点赞");
        int self_user_id = 0;
        if (token != null && !token.equals("") && redisTemplate.opsForValue().get(token) != null) self_user_id = (int) redisTemplate.opsForValue().get(token);
        return likeService.likeAction(self_user_id,video_id,action_type);
    }
    @RequestMapping("/douyin/favorite/list/")
    @ResponseBody
    public FeedResponse likeList(int user_id, String token) {
        System.out.println("点赞列表");
        int self_user_id = 0;
        if (token != null && !token.equals("") && redisTemplate.opsForValue().get(token) != null) self_user_id = (int) redisTemplate.opsForValue().get(token);
        return new FeedResponse(0,"获取视频流成功",videoService.getVideoList(user_id, 0));
    }
}
