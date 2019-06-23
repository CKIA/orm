package com.ckia.test.receive;

import com.ckia.test.enums.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author ckia
 * @description: TODO
 * @title: ckia学习累积使用
 * @projectName orm
 * @date 19-6-23上午9:47
 */
@Component
@RabbitListener(queues = Constants.DEFAULT_QUEUE_NAME)
public class ReceiveMessage {

    private static Logger logger = LoggerFactory.getLogger(ReceiveMessage.class);


    @RabbitHandler
    public void receiveMessage(Map<String,Object> userName) {
        logger.info("消息接收成功，用户名为：" + userName);
    }
}
