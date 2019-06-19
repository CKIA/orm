package com.ckia.test.annotation.stringValideation;

/**
 * @author ckia
 * @description: TODO
 * @title: ckia学习累积使用
 * @projectName orm
 * @date 19-6-9下午2:42
 */
public class TestMain {

    public static void showA(String srcShowString, A srcObj) {
        System.out.println("----------------" + srcShowString + "代理中-----------");
        srcObj = ProxyBase.getInstance(srcObj);
        srcObj.say1();
        System.out.println();
        srcObj.say2();
        System.out.println();
    }

    public static void main(String[] args) {
        A a = new B();
        showA("B", a);
        a = new C();
        showA("C", a);
    }
}
