package com.example.douyin.service;

import com.example.douyin.dao.FollowMapper;
import com.example.douyin.dto.User;
import com.example.douyin.dto.UserResponse;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FollowService {
    @Autowired
    FollowMapper followMapper;
    @Autowired
    RabbitTemplate rabbitTemplate;
    public Map<String, String> followAction (int user_id, int to_user_id, int action_type) {
        if (user_id == 0) return Map.of("status_code", "1", "status_msg","请重新登陆");
        if (user_id == to_user_id) return Map.of("status_code", "1", "status_msg","无法关注自己");
        boolean ifFollow = followMapper.ifFollowByUserIdAndAuthorId(user_id, to_user_id);
        String msg = "关注成功";
        if (action_type == 2) msg = "取消关注";
        if (ifFollow) {
            rabbitTemplate.convertAndSend("follow.queue", Map.of("action_type",action_type,"user_id",user_id,"to_user_id",to_user_id));
            System.out.println("向关注队列发送了关注消息！");
            return Map.of("status_code", "0", "status_msg",msg);
        }
        followMapper.insertFollowByUserIdAndVideoId(user_id,to_user_id,action_type);
        return Map.of("status_code", "0", "status_msg",msg);
    }

    public UserResponse getFollowList(int user_id, int self_user_id) {
        List<User> userList = followMapper.getFollowListByUserId(user_id);
        for (User user : userList) {
            user.setIs_follow(followMapper.ifFollowByUserIdAndAuthorId(self_user_id,user.getId()));
        }
        return new UserResponse(0, "获取关注列表", userList);
    }

    public UserResponse getFollowerList(int user_id, int self_user_id) {
        List<User> userList = followMapper.getFollowerListByUserId(user_id);
        for (User user : userList) {
            user.setIs_follow(followMapper.ifFollowByUserIdAndAuthorId(self_user_id,user.getId()));
        }
        return new UserResponse(0, "获取粉丝列表", userList);
    }

    @RabbitListener(queues = "follow.queue")
    public void listenlikeQueueMsg(Map<String, Integer> map) {
        followMapper.setFollowByUserIdAndFollowId(map.get("action_type"),map.get("user_id"),map.get("to_user_id"));
        System.out.println("消费者拿到了关注消息！并且执行了关注操作！");
    }
}
