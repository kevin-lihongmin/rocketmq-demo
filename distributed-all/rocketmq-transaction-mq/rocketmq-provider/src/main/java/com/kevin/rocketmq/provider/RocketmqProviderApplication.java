package com.kevin.rocketmq.provider;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.Resource;

/**
 *
 * @author kevin
 * @date 2021/7/3 18:09
 * @since 1.0.0
 */
@SpringBootApplication
public class RocketmqProviderApplication implements CommandLineRunner, ApplicationContextAware {

    public static void main(String[] args) {
        SpringApplication.run(RocketmqProviderApplication.class, args);
    }

    private ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void run(String... args) {
        SenderRocketmq bean = applicationContext.getBean(SenderRocketmq.class);
        bean.sendMq();
    }

}
