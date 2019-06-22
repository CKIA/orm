package com.ckia.test.mapper;

import com.ckia.test.pojo.UserDto;

import java.util.List;

/**
 * @author ckia
 * @description: mybatis查询服务
 * @title: ckia学习累积使用
 * @projectName orm
 * @date 19-6-9上午2:54
 */
//@Mapper
public interface TransactionalUserMapper {

    UserDto getUser(UserDto user);

    void saveUser(UserDto user);

    List<UserDto> getUserList();

    void updateUser(UserDto user);

    void saveListUser(List<UserDto> users);
}
