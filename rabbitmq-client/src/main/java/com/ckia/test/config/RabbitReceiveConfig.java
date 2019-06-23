package com.ckia.test.config;

import com.ckia.test.enums.Constants;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ckia
 * @description: TODO
 * @title: ckia学习累积使用
 * @projectName orm
 * @date 19-6-20下午6:05
 */
//@Configuration
public class RabbitReceiveConfig {

    @Bean
    public Queue queue(){
        return new Queue(Constants.DEFAULT_QUEUE_NAME,true);
    }

    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(Constants.DEFAULT_EXCHANGE_NAME);
    }

    @Bean
    public Binding binding(){
        return BindingBuilder.bind(queue()).to(directExchange()).with(Constants.DEFAULT_EXCHANGE_NAME);
    }
}
