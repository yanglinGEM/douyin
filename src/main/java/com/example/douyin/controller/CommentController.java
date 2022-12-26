package com.example.douyin.controller;

import com.example.douyin.dto.CommentResponse;
import com.example.douyin.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.Map;

@Controller
public class CommentController {
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    CommentService commentService;
    @RequestMapping("/douyin/comment/action/")
    @ResponseBody
    public CommentResponse comment(String token, int video_id, int action_type, String comment_text) {
        System.out.println("评论");
        int self_user_id = 0;
        if (token != null && !token.equals("") && redisTemplate.opsForValue().get(token) != null) self_user_id = (int) redisTemplate.opsForValue().get(token);
        return commentService.comment(self_user_id, video_id, comment_text, LocalDateTime.now(), action_type);
    }
    @RequestMapping("/douyin/comment/list/")
    @ResponseBody
    public CommentResponse commentList(String token, int video_id) {
        System.out.println("评论列表");
        int self_user_id = 0;
        if (token != null && !token.equals("") && redisTemplate.opsForValue().get(token) != null) self_user_id = (int) redisTemplate.opsForValue().get(token);
        return commentService.commentList(video_id);
    }
}
