package com.ckia.test.controller;

import com.ckia.test.entity.LoggerDto;
import com.ckia.test.entity.UserDto;
import com.ckia.test.service.LogService;
import com.ckia.test.service.UserService;
import com.ckia.test.service.impl.TestService;
import com.ckia.test.utils.GenerateStringUtil;
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
    private TestService testService;

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
        UserDto user = userService.getUser(userDto);
        LoggerDto dto = new LoggerDto();
        dto.setL_userId(userDto.getU_id());
        logService.getLoggerDto(dto);
        System.out.println(dto);
        System.out.println(user);
        System.out.println(dto);
        return user;
    }
    @PostMapping("query2")
    public UserDto getUser2(UserDto userDto){
        UserDto user = userService.getUser(userDto);
        LoggerDto dto = new LoggerDto();
        dto.setL_userId(userDto.getU_id());
        System.out.println(dto);
        logService.getLoggerDto(dto);
        System.out.println(user);
        System.out.println(dto);
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
        LoggerDto dto = new LoggerDto();
        dto.setL_detail("user/save");
        dto.setL_type(1);
        dto.setL_userId(userDto.getU_id());
        logService.saveLoggerDto(dto);
        System.out.println("time:"+(System.currentTimeMillis()-start));
        System.out.println("user:"+userDto+",log:"+dto);
        return userDto;

    }
    @GetMapping("save2")
    public UserDto saveUser2(){
        long start = System.currentTimeMillis();
        UserDto userDto = new UserDto();
        String random = GenerateStringUtil.generateName();
        userDto.setU_name(random);
        userDto.setU_password(random);
        userService.saveUser2(userDto);
        LoggerDto dto = new LoggerDto();
        dto.setL_detail("user/save2");
        dto.setL_type(1);
        dto.setL_userId(userDto.getU_id());
        logService.saveLoggerDto(dto);
        System.out.println("time:"+(System.currentTimeMillis()-start));
        System.out.println("user:"+userDto+",log:"+dto);
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
