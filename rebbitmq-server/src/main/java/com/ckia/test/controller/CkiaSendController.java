package com.ckia.test.controller;

import com.ckia.test.sendMessage.SendMessageService;
import com.ckia.test.sendMessage.impl.Sender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author ckia
 * @description: TODO
 * @title: ckia学习累积使用
 * @projectName orm
 * @date 19-6-23上午9:24
 */
@RestController
@RequestMapping("ckia")
public class CkiaSendController {

    private static Logger logger = LoggerFactory.getLogger(Sender.class);

    @Autowired
    private SendMessageService messageService;

    @PostMapping("save")
    public void setMessage(Map<String,Object> user){
        messageService.sendMessage(user);
        logger.info("setMessage方法>>>>>>>>>>>>>发送成功"+user);
        for(int index=0;index<10000;index++){
            messageService.sendMessage(user);
//            logger.info("setMessage循环方法"+index);
        }
    }
}
