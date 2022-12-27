package com.example.douyin.service;

import com.example.douyin.dao.LikeMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class LikeService {
    @Autowired
    LikeMapper likeMapper;
    @Autowired
    RabbitTemplate rabbitTemplate;

    public Map<String, String> likeAction(int self_user_id, int video_id, int action_type) {
        if (self_user_id == 0) return Map.of("status_code", "1", "status_msg","请重新登陆");
        boolean ifLike = likeMapper.ifLikeByUserIdAndVideoId(self_user_id, video_id);
        String msg = "点赞成功";
        if (action_type == 2) msg = "取消点赞";
        if (ifLike) {
            rabbitTemplate.convertAndSend("like.queue", Map.of("action_type",action_type,"self_user_id",self_user_id,"video_id",video_id));
            System.out.println("向点赞队列发送消息");
            return Map.of("status_code", "0", "status_msg",msg);
        }
        likeMapper.insertLikeByUserIdAndVideoId(self_user_id,video_id,action_type);
        return Map.of("status_code", "0", "status_msg",msg);
    }

    @RabbitListener(queues = "like.queue")
    public void listenlikeQueueMsg(Map<String, Integer> map) {
        likeMapper.setLikeByUserId(map.get("action_type"),map.get("self_user_id"),map.get("video_id"));
        System.out.println("消费者拿到了点赞消息！并且执行了点赞操作！");
    }
}
