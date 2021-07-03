package com.kevin.rocketmq.consumer;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RocketMQMessageListener(consumerGroup = "transaction-half-mq", topic = "test_topic")
public class RocketmqHalfListener implements RocketMQListener<User> {

//    @Autowired
//    UserMoneyService memberOrderService;

    public void onMessage(User userMessage) {
        log.info("收到-用户余额变动-半消息");
        try {

        } catch (Exception e) {
            log.info("更改余额错误: " + e.getMessage());
            e.printStackTrace();
        }
        log.info(JSON.toJSONString(userMessage));
    }
}