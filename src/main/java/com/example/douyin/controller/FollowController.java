package com.example.douyin.controller;

import com.example.douyin.dto.UserResponse;
import com.example.douyin.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class FollowController {
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    FollowService followService;

    @RequestMapping("/douyin/relation/action/")
    @ResponseBody
    public Map<String, String> followAction(String token, int to_user_id, int action_type) {
        System.out.println("关注");
        int self_user_id = 0;
        if (token != null && !token.equals("") && redisTemplate.opsForValue().get(token) != null) self_user_id = (int) redisTemplate.opsForValue().get(token);
        return followService.followAction(self_user_id,to_user_id,action_type);
    }
    @RequestMapping("/douyin/relation/follow/list/")
    @ResponseBody
    public UserResponse getFollowList(int user_id, String token) {
        System.out.println("关注列表");
        int self_user_id = 0;
        if (token != null && !token.equals("") && redisTemplate.opsForValue().get(token) != null) self_user_id = (int) redisTemplate.opsForValue().get(token);
        return followService.getFollowList(user_id, self_user_id);
    }

    @RequestMapping("/douyin/relation/follower/list/")
    @ResponseBody
    public UserResponse getFollowerList(int user_id, String token) {
        System.out.println("粉丝列表");
        int self_user_id = 0;
        if (token != null && !token.equals("") && redisTemplate.opsForValue().get(token) != null) self_user_id = (int) redisTemplate.opsForValue().get(token);
        return followService.getFollowerList(user_id, self_user_id);
    }
}
