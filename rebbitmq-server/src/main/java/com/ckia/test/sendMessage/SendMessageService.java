package com.ckia.test.sendMessage;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * @author ckia
 * @description: TODO
 * @title: ckia学习累积使用
 * @projectName orm
 * @date 19-6-23上午9:16
 */
public interface SendMessageService extends RabbitTemplate.ConfirmCallback {

    /**
    　* @description: 消息发送
    　* @author ckia
    　* @param
    　* @return
    　* @throws
    　* @date 19-6-23 上午9:19
    　*/
    void sendMessage(Object object);
}
