package com.ckia.test.service;


import java.util.List;

/**
 * @Description:
 * @ProjectName: testaop
 * @Author: ckia
 * @Date: 19-5-23 下午2:23
 * @Version: 1.0
 */
public interface UserService<T> {

    List<T> getUserList();

    T getUser(T user);

    void saveUser(T user);

    void saveUser2(T user);

    void saveListUser(List<T> users);

    void updateUser(T user);

    void test();
}
