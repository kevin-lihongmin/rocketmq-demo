package com.kevin.rocketmq.provider;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.messaging.Message;

/**
 *  事务消息监听
 *
 * @author kevin
 * @date 2021/7/3 18:29
 * @since 1.0.0
 */
@Slf4j
@RocketMQTransactionListener(txProducerGroup = "transaction-half-mq")
public class MqListener implements RocketMQLocalTransactionListener {

    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object arg) {
        try {
            Object userMoneyParams = message.getPayload();
            log.info("消息-args:" + arg);
            // 消息主体加密无法获取
            log.info("消息-主体:" + JSON.toJSONString(userMoneyParams));
            log.info("消息-主体-头部:"+message.getHeaders().get(RocketMQHeaders.TRANSACTION_ID));
            log.info("半消息-本地-处理完成");
            return RocketMQLocalTransactionState.COMMIT;
        } catch (Exception e) {
            log.warn("半消息-本地-发生异常,回滚: "+e.getMessage());
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }

    /**
     * 生产者-消息处理超时， rocketmq 回调该方法判断半实物该提交还是回滚
     *
     * @param message 消息信息
     * @return 消息的状态
     */
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        // 查询消息是否已经处理
        String messageID = String.valueOf(message.getHeaders().get("tsca-half-message-id"));
//        Object messageData = this.redisUtil.getValue(messageID, String.class);
        Object messageData = null;
        if (messageData != null && messageData.equals("ok")) {
            // 超时且消息已经处理完毕
            log.info("半消息-本地消息超时-且已经处理完毕");
            return RocketMQLocalTransactionState.COMMIT;
        } else {
            log.info("半消息-本地消息超时-且未处理完毕");
            // 超时且消息未处理完毕
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }
}
