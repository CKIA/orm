package com.ckia.test.service.impl;

import com.ckia.test.annotation.dataBase.DataBase;
import com.ckia.test.annotation.stringValideation.StringValidation;
import org.springframework.stereotype.Component;

/**
 * @author ckia
 * @description: 自测试自定义注解配置
 * @title: ckia学习累积使用
 * @projectName orm
 * @date 19-6-9上午5:34
 */
@Component
public class TestService {
    @StringValidation("qqqqqqqqqq")
    //默认(第一)数据源
//    @DataBase("db1")
    public void dbTest(){
        System.out.println("dbT1");
    }

    //第二数据源
//    @DataBase("db2")
    public void dbTest2(){
        System.out.println("dbT2");
    }
}
