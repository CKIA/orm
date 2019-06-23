package com.ckia.test.sendMessage.impl;

import com.ckia.test.enums.Constants;
import com.ckia.test.sendMessage.SendMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ckia
 * @description: TODO
 * @title: ckia学习累积使用
 * @projectName orm
 * @date 19-6-23上午9:03
 */
@Component
public class Sender implements SendMessageService {

    private static Logger logger = LoggerFactory.getLogger(Sender.class);

    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * @description: 消息发送
     * @author ckia
     * @param
     * @return
     * @throws
     * @date 19-6-23 上午9:19
     */
    @Override
    public void sendMessage(Object object) {
        amqpTemplate.convertAndSend(Constants.DEFAULT_QUEUE_NAME,object);
    }
    /**
    　* @description: 消息回调信息
    　* @author ckia
    　* @param
    　* @return
    　* @throws
    　* @date 19-6-23 上午9:20
    　*/
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        logger.info("confirm回调方法>>>>>>>>>>>>>回调消息ID为: " + correlationData.getId());
        if (ack) {
            logger.info("confirm回调方法>>>>>>>>>>>>>消息发送成功");
        } else {
            logger.info("confirm回调方法>>>>>>>>>>>>>消息发送失败" + cause);
        }
    }
}
