package com.example.douyin.service;

import com.example.douyin.dao.FollowMapper;
import com.example.douyin.dao.UserMapper;
import com.example.douyin.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class UserService {
    @Autowired
    FollowMapper followMapper;
    @Autowired
    UserMapper userMapper;
    public User getUserInfoByUserId(int author_id, int self_user_id) {
        User user = userMapper.selectById(author_id);
        if (user == null) return user;
        user.setFollow_count(followMapper.getFollowCountByUserId(author_id));
        user.setFollower_count(followMapper.getFollowerCountByUserId(author_id));
        boolean ifFollow = followMapper.ifFollowByUserIdAndAuthorId(self_user_id, author_id);
        if (ifFollow) {
            user.setIs_follow(followMapper.getCancelByUserIdAndAuthorId(self_user_id,author_id) == 1 ? true : false);
        } else user.setIs_follow(ifFollow);
        return user;
    }

    public String getMD5Str(String password) {
        byte[] digest = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("md5");
            digest  = md5.digest(password.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return new BigInteger(1, digest).toString(16);
    }
}
