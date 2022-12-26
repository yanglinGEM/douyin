package com.example.douyin.controller;

import com.example.douyin.dao.CommentMapper;
import com.example.douyin.dao.LikeMapper;
import com.example.douyin.dao.VideoMapper;
import com.example.douyin.dto.FeedResponse;
import com.example.douyin.dto.User;
import com.example.douyin.dto.Video;
import com.example.douyin.service.UserService;
import com.example.douyin.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Controller
public class FeedController {

    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    VideoService videoService;
    @RequestMapping("/douyin/feed")
    @ResponseBody
    FeedResponse feed(String token) {
        System.out.println("视频流");
        int user_id = 0;
        if (token != null && !token.equals("") && redisTemplate.opsForValue().get(token) != null) user_id = (int) redisTemplate.opsForValue().get(token);
        return new FeedResponse(0,"获取视频流成功",videoService.getVideoList(user_id, -1));
    }

    @RequestMapping("/douyin/publish/action/")
    @ResponseBody
    Map<String, String> publishAction(String token, MultipartFile data, String title) throws IOException {
        int author_id = -1;
        if (token != null && !token.equals("") && redisTemplate.opsForValue().get(token) != null) author_id = (int) redisTemplate.opsForValue().get(token);
        return videoService.publishVideo(author_id, data, title);
    }

    @RequestMapping("/douyin/publish/list/")
    @ResponseBody
    FeedResponse publishList(int user_id, String token) {
        System.out.println("发布列表");
        int self_user_id = 0;
        if (token != null && !token.equals("") && redisTemplate.opsForValue().get(token) != null) self_user_id = (int) redisTemplate.opsForValue().get(token);
        return new FeedResponse(0,"获取视频流成功",videoService.getVideoList(self_user_id, user_id));
    }
}
