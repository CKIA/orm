package com.ckia.test.controller;

import com.ckia.test.annotation.dataBase.DataBase;
import com.ckia.test.annotation.stringValideation.StringValidation;
import com.ckia.test.enums.DataSourceType;
import com.ckia.test.mapper.UserMapper;
import com.ckia.test.pojo.LoggerDto;
import com.ckia.test.pojo.UserDto;
import com.ckia.test.service.LogService;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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

    @Resource
    private LogService logService;

    @Autowired
    @Qualifier("TransactionalUserService")
    private UserService<UserDto> transactionalUserService;

    @GetMapping("queryList")
    public List<UserDto> getUserList(){
        return userService.getUserList();
    }

    @GetMapping("test")
    public void test(){
        userService.test();
    }

    @PostMapping("query")
    public UserDto getUser(UserDto userDto){
        LoggerDto dto = new LoggerDto();
        dto.setL_detail("user/query");
        dto.setL_type(0);
        logService.saveLoggerDto(dto);
        UserDto user = userService.getUser(userDto);

        System.out.println(user);
        return user;
    }
    @PostMapping("query2")
    @DataBase(DataSourceType.mybatis2)
    public UserDto getUser2(UserDto userDto){
        LoggerDto dto = new LoggerDto();
        dto.setL_detail("user/query2");
        dto.setL_type(0);
        logService.saveLoggerDto(dto);
        UserDto user = transactionalUserService.getUser(userDto);

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
        LoggerDto dto = new LoggerDto();
        dto.setL_detail("user/save");
        dto.setL_type(1);
        logService.saveLoggerDto(dto);
        long start = System.currentTimeMillis();
        UserDto userDto = new UserDto();
        String random = GenerateStringUtil.generateName();
        userDto.setU_name(random);
        userDto.setU_password(random);
        userService.saveUser(userDto);
        System.out.println("time:"+(System.currentTimeMillis()-start));
        return userDto;

    }
    @GetMapping("save2")
    @DataBase(DataSourceType.mybatis2)
    public UserDto saveUser2(){
        LoggerDto dto = new LoggerDto();
        dto.setL_detail("user/save2");
        dto.setL_type(1);
        logService.saveLoggerDto(dto);
        long start = System.currentTimeMillis();
        UserDto userDto = new UserDto();
        String random = GenerateStringUtil.generateName();
        userDto.setU_name(random);
        userDto.setU_password(random);
        transactionalUserService.saveUser(userDto);
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
        return "success";

    }
    @GetMapping("saveListUser2")
    @DataBase(DataSourceType.mybatis2)
    public String saveListUser2(){
        ArrayList<UserDto> list = new ArrayList<>();
        for (int index=10000;index>0;index--){
            UserDto userDto = new UserDto();
            String random = GenerateStringUtil.generateName();
            userDto.setU_name(index+random+"C");
            userDto.setU_password(random);
            list.add(userDto);
        }
        long start = System.currentTimeMillis();
        transactionalUserService.saveListUser(list);
        System.out.println("time:"+(System.currentTimeMillis()-start));
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
