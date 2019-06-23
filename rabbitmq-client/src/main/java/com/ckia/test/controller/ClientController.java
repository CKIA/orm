package com.ckia.test.controller;

import com.ckia.test.MybatisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ckia
 * @description: TODO
 * @title: ckia学习累积使用
 * @projectName orm
 * @date 19-6-23下午2:14
 */
@RestController
@RequestMapping("client")
public class ClientController {

    private static Logger logger = LoggerFactory.getLogger(ClientController.class);


//    @Autowired
//    private MybatisService mybatisService;

    @PostMapping("save")
    public void client(){
        logger.info("请求接收成功");
//        mybatisService.save();
    }
}
