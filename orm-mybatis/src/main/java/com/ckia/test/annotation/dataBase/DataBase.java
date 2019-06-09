package com.ckia.test.annotation.dataBase;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ckia
 * @description: 指定数据源注解对象，用于在使用多数据源时指定使用的数据源
 * @title: ckia学习累积使用
 * @projectName orm
 * @date 19-6-9上午5:05
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface DataBase {
    String value();
}