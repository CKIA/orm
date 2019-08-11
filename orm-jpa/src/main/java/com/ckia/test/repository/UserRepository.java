package com.ckia.test.repository;

import com.ckia.test.entity.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ckia
 * @description: jpa查询服务
 * @title: ckia学习累积使用
 * @projectName orm
 * @date 19-6-9上午2:54
 */
@Repository
public interface UserRepository extends JpaRepository<UserDto,Integer> {


}
