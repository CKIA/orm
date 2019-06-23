package com.ckia.test;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author ckia
 * @description: TODO
 * @title: ckia学习累积使用
 * @projectName orm
 * @date 19-6-23下午1:40
 */
@FeignClient(name ="MybatisService",path = "http://localhost:8083/")
public interface MybatisService {

    @PostMapping("user/save2")
    void save();
    @PostMapping("user/query2")
    void query();
}
