package com.ckia.test.controller;

import com.ckia.test.mapper.UserMapper;
import com.ckia.test.pojo.UserDto;
import com.ckia.test.service.UserService;
import com.ckia.test.utils.GenerateStringUtil;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
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

    @Autowired
    private SqlSessionTemplate sessionTemplate;

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
        return userService.getUser(userDto);
    }

    @GetMapping("update")
    public void updateUser(){
        UserDto userDto = new UserDto();
        userDto.setU_id(33);
        userDto.setU_name(GenerateStringUtil.generateName()+"dd");
        userService.updateUser(userDto);
    }

    @GetMapping("save")
    public String saveUser(){
        long start = System.currentTimeMillis();
        UserDto userDto = new UserDto();
        String random = GenerateStringUtil.generateName();
        userDto.setU_name(random);
        userDto.setU_password(random);
        userService.saveUser(userDto);
        System.out.println("time:"+(System.currentTimeMillis()-start));
        return "success";

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
        SqlSession session = sessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH);
        UserMapper mapper = session.getMapper(UserMapper.class);
        long start2 = System.currentTimeMillis();
        ArrayList<UserDto> list2 = new ArrayList<>();
        for (int index=10000;index>0;index--){
            UserDto userDto = new UserDto();
            String random = GenerateStringUtil.generateName();
            userDto.setU_name(index+random+"D");
            userDto.setU_password(random);
            list2.add(userDto);
        }
        mapper.saveListUser(list2);
        session.commit();
        System.out.println("sqlSessionTemplate-time:"+(System.currentTimeMillis()-start2));
        return "success";

    }
}
