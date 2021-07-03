package com.kevin.rocketmq.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author kevin
 * @date 2021/7/3 20:46
 * @since 1.0.0
 */
@RestController
public class RequestController {

    @Autowired
    private SenderRocketmq senderRocketmq;

    @GetMapping("/test/mq")
    public void test() {
        senderRocketmq.sendMq();
    }

}
