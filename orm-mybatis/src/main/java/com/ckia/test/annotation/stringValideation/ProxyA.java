package com.ckia.test.annotation.stringValideation;

/**
 * @author ckia
 * @description: 代理的实现类
 * @title: ckia学习累积使用
 * @projectName orm
 * @date 19-6-9下午2:40
 */
class ProxyA extends ProxyBase {
    public void beforeAction() {
        System.out.print("<前>");
    }

    public void afterAction() {
        System.out.print("<后>");
    }
}
//接口
interface A {
    void say1();

    void say2();
}