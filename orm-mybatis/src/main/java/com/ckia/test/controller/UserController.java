package com.ckia.test.controller;

import com.ckia.test.annotation.dataBase.DataBase;
import com.ckia.test.annotation.stringValideation.StringValidation;
import com.ckia.test.mapper.UserMapper;
import com.ckia.test.pojo.UserDto;
import com.ckia.test.service.UserService;
import com.ckia.test.service.impl.TestService;
import com.ckia.test.utils.GenerateStringUtil;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 基本调用对象
 * @ProjectName: testaop
 * @Author: ckia
 * @Date: 19-5-23 下午2:21
 * @Version: 1.0
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    @Qualifier("myBaitisService")
    private UserService<UserDto> userService;


    @GetMapping("queryList")
    public List<UserDto> getUserList(){
        return userService.getUserList();
    }

    @GetMapping("test")
    public void test(){
        userService.test();
    }

    @GetMapping("query")
    public UserDto getUser(){
        UserDto userDto = new UserDto();
        userDto.setU_id(33);
//        userDto.setU_name("891623dd");
//        return userDto;
        UserDto user = userService.getUser(userDto);

        System.out.println(user);
        return user;
    }

    @GetMapping("update")
    public void updateUser(){
        UserDto userDto = new UserDto();
        userDto.setU_id(33);
        userDto.setU_name(GenerateStringUtil.generateName()+"dd");
        userService.updateUser(userDto);
    }

    @GetMapping("save")
    public UserDto saveUser(){
        long start = System.currentTimeMillis();
        UserDto userDto = new UserDto();
        String random = GenerateStringUtil.generateName();
        userDto.setU_name(random);
        userDto.setU_password(random);
        userService.saveUser(userDto);
        System.out.println("time:"+(System.currentTimeMillis()-start));
        return userDto;

    }
    @GetMapping("saveListUser")
    public String saveListUser(){
        ArrayList<UserDto> list = new ArrayList<>();
        for (int index=10000;index>0;index--){
            UserDto userDto = new UserDto();
            String random = GenerateStringUtil.generateName();
            userDto.setU_name(index+random+"C");
            userDto.setU_password(random);
            list.add(userDto);
        }
        long start = System.currentTimeMillis();
        userService.saveListUser(list);
        System.out.println("time:"+(System.currentTimeMillis()-start));
//        SqlSession session = sessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH);
//        UserMapper mapper = session.getMapper(UserMapper.class);
        long start2 = System.currentTimeMillis();
//        ArrayList<UserDto> list2 = new ArrayList<>();
//        for (int index=10000;index>0;index--){
//            UserDto userDto = new UserDto();
//            String random = GenerateStringUtil.generateName();
//            userDto.setU_name(index+random+"D");
//            userDto.setU_password(random);
//            list2.add(userDto);
//        }
//        mapper.saveListUser(list2);
//        session.commit();
        System.out.println("sqlSessionTemplate-time:"+(System.currentTimeMillis()-start2));
        return "success";

    }

    @Autowired
    TestService testService;

    //默认(第一)数据源
    @GetMapping("db1")
    public void dbTest(){
        testService.dbTest();
    }

    //第二数据源
    @GetMapping("db2")
    public void dbTest2(){
        testService.dbTest2();
    }
}
