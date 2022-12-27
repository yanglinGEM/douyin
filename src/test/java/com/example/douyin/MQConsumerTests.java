package com.example.douyin;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MQConsumerTests {
    @RabbitListener(queues = "simple.queue")
    public void listenSimpleQueueMsg(String msg) {
        System.out.println("消费者拿到了消息！" + msg + "并且消费掉了！");
    }
}
