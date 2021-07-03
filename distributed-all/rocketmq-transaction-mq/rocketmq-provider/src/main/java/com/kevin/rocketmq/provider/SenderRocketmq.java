package com.kevin.rocketmq.provider;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 *
 * @author kevin
 * @date 2021/7/3 18:20
 * @since 1.0.0
 */
@Component
public class SenderRocketmq {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public void sendMq(){
        //实体类User
        User user = new User("no-999","testUser");

//        //发送自定对象
//        rocketMQTemplate.convertAndSend("test_topic", user);

        // 用户数据变更-参数
//        UserOrder userOrder = this.userOrderMapper.selectByPrimaryKey(1);

        rocketMQTemplate.sendMessageInTransaction(
                "transaction-half-mq", "test_topic",
                // 半消息-数据体
                MessageBuilder
                        .withPayload(user)
                        .setHeader(RocketMQHeaders.TRANSACTION_ID, UUID.randomUUID())
                        .build(),
                new Object()
        );
    }

}
