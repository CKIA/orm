package com.ckia.test.service.impl;

import com.ckia.test.repository.*;
import com.ckia.test.entity.UserDto;
import com.ckia.test.service.UserService;
import com.ckia.test.utils.GenerateStringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description: 用户操作服务类
 * @ProjectName: testaop
 * @Author: ckia
 * @Date: 19-5-23 下午2:23
 * @Version: 1.0
 */
@Component("myBaitisService")
public class UserServiceImpl implements UserService<UserDto> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDto getUser(UserDto user) {
        return userRepository.findOne(Example.of(user)).get();
    }

    @Override
    public List<UserDto> getUserList() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = RuntimeException.class)
    public void saveUser(UserDto user) {
        userRepository.save(user);
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = RuntimeException.class)
    public void saveUser2(UserDto user) {
        userRepository.save(user);
    }


    @Override
    public void saveListUser(List<UserDto> users) {
        userRepository.saveAll(users);
    }

    @Override
    public void updateUser(UserDto user) {
        userRepository.saveAndFlush(user);
    }

//    @Override
    public void test2() {
        UserDto dto = new UserDto();
        dto.setU_id(33);
        System.out.println(dto);
        String generateName = GenerateStringUtil.generateName();
        dto.setU_name(generateName);
        this.updateUser(dto);
        System.out.println(dto);
    }

    @Override
    public void test() {
        UserDto dto = new UserDto();
        dto.setU_id(33);
        dto = this.getUser(dto);
        System.out.println(dto);
        String generateName = GenerateStringUtil.generateName();
        dto.setU_name(generateName);
        dto = this.getUser(dto);
        System.out.println(dto);

    }

}
