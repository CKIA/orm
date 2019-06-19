package com.ckia.test.service.impl;

import com.ckia.test.mapper.UserMapper;
import com.ckia.test.pojo.UserDto;
import com.ckia.test.service.UserService;
import com.ckia.test.utils.GenerateStringUtil;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    private UserMapper userMapper;

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;
    @Autowired
    @Qualifier("TransactionalUserService")
    private UserService<UserDto> transactionalUserService;

    @Override
    public UserDto getUser(UserDto user) {
        return userMapper.getUser(user);
    }

    @Override
    public List<UserDto> getUserList() {
        return userMapper.getUserList();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = RuntimeException.class)
    public void saveUser(UserDto user) {
        userMapper.saveUser(user);
        try {
            transactionalUserService.saveUser(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void saveListUser(List<UserDto> users) {
        userMapper.saveListUser(users);
    }

    @Override
    public void updateUser(UserDto user) {
        userMapper.updateUser(user);
    }

//    @Override
    public void test2() {
        UserMapper mapper = sqlSessionTemplate.getMapper(UserMapper.class);
        UserDto dto = new UserDto();
        dto.setU_id(33);
        dto = mapper.getUser(dto);
        System.out.println(dto);
        String generateName = GenerateStringUtil.generateName();
        dto.setU_name(generateName);
//        this.updateUser(dto);
        dto = mapper.getUser(dto);
        System.out.println(dto);
    }

    @Override
    public void test() {
        UserDto dto = new UserDto();
        dto.setU_id(33);
        dto = this.getUser(dto);
        System.out.println(dto);
        UserMapper mapper = sqlSessionTemplate.getMapper(UserMapper.class);
        String generateName = GenerateStringUtil.generateName();
        dto.setU_name(generateName);
//        mapper.updateUser(dto);
        dto = this.getUser(dto);
        System.out.println(dto);

    }

}
