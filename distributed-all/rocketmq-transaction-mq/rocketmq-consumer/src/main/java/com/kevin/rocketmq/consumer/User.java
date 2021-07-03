package com.kevin.rocketmq.consumer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  消息体模拟
 *
 * @author kevin
 * @date 2021/7/3 18:22
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String userId;

    private String userName;

}
