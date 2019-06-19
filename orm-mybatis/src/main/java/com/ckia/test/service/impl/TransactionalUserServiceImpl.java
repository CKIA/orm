package com.ckia.test.service.impl;

import com.ckia.test.annotation.dataBase.DataBase;
import com.ckia.test.mapper.UserMapper;
import com.ckia.test.pojo.UserDto;
import com.ckia.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ckia
 * @description: TODO
 * @title: ckia学习累积使用
 * @projectName orm
 * @date 19-6-10下午3:28
 */
@Component("TransactionalUserService")
public class TransactionalUserServiceImpl implements UserService<UserDto> {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserDto> getUserList() {
        return null;
    }

    @Override
    public UserDto getUser(UserDto user) {
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
//    @DataBase("mybatis-2")
    public void saveUser(UserDto user) {
        user.setU_id(null);
        user.setU_name(user.getU_name()+"ere");
        userMapper.saveUser(user);
        throw new RuntimeException("Rollback this transaction!");
    }

    @Override
    public void saveListUser(List<UserDto> users) {

    }

    @Override
    public void updateUser(UserDto user) {

    }

    @Override
    public void test() {

    }
}
