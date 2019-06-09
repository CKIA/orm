package com.ckia.test.annotation.stringValideation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ckia
 * @description: 字符串验证
 * @title: ckia学习累积使用
 * @projectName orm
 * @date 19-6-9上午4:29
 */
@Target(value = {ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface StringValidation {

    String value() default "";
}
