package com.example.douyin.controller;

import com.example.douyin.dao.FollowMapper;
import com.example.douyin.dao.UserMapper;
import com.example.douyin.dto.User;
import com.example.douyin.dto.UserResponse;
import com.example.douyin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.util.Map;
import java.util.UUID;

@Controller
public class UserController {
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserService userService;
    @Autowired
    RedisTemplate redisTemplate;
    @RequestMapping("/douyin/user/register/")
    @ResponseBody
    public Map<String, String> register(String username, String password) {
        User user = userMapper.selectByName(username);
        if (user != null) {
            System.out.println("用户已被注册");
            return Map.of("status_code","-1","status_msg","用户已被注册");
        }
        user = new User(username, userService.getMD5Str(password));
        if (!userMapper.insert(user))  return Map.of("status_code","-1","status_msg","注册失败");
        User c = userMapper.selectByName(user.getName());
        String token = UUID.randomUUID() + "";
        redisTemplate.opsForValue().set(token, c.getId(), Duration.ofMinutes(30L));
        System.out.println("注册成功");
        return Map.of("status_code","0","status_msg","注册成功","user_id", ""+c.getId(),"token",token);
    }

    @RequestMapping("/douyin/user/login/")
    @ResponseBody
    public Map<String, String> login(String username, String password) {
        User b = userMapper.selectByName(username);
        if (b != null && userService.getMD5Str(password).equals(b.getPassword())) {
            System.out.println("登陆成功");
            String token = UUID.randomUUID() + "";
            redisTemplate.opsForValue().set(token, b.getId(), Duration.ofMinutes(30L));
            return Map.of("status_code","0","status_msg","登陆成功","user_id",""+b.getId(),"token",token);
        }
        System.out.println("用户名或密码错误");
        return Map.of("status_code","1","status_msg","用户名或密码错误");
    }

    @RequestMapping("/douyin/user/")
    @ResponseBody
    public UserResponse loginSuccess(int user_id, String token) {
        System.out.println("用户信息");
        Object id = 0;
        if (token != null && !token.equals("") && redisTemplate.opsForValue().get(token) != null) id = redisTemplate.opsForValue().get(token);
        User userInfo = userService.getUserInfoByUserId(user_id,(int)id);
        return new UserResponse(0,"获取用户信息成功",userInfo);
    }
}
