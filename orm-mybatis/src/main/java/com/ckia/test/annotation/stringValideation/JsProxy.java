package com.ckia.test.annotation.stringValideation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * @author ckia
 * @description: 自定义的注解代理对象:必须是接口对象赋值实现类
 * @title: ckia学习累积使用
 * @projectName orm
 * @date 19-6-9下午2:38
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = { TYPE, METHOD })
public @interface JsProxy {

    /**
     * 设置代理对象的类
     */
    public Class<?> value();

    /**
     * 设置拦截的类型，默认前后拦截
     */
    public ProxyType[] type() default { ProxyType.AFTER, ProxyType.BEFORE };

}