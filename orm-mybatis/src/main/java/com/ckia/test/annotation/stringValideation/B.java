package com.ckia.test.annotation.stringValideation;

/**
 * @author ckia
 * @description: TODO
 * @title: ckia学习累积使用
 * @projectName orm
 * @date 19-6-9下午2:41
 */
class B implements A {
    // @JsProxy( ProxyA.class)
    @JsProxy(value = ProxyA.class, type = { ProxyType.BEFORE })
    public void say1() {
        System.out.print("b1");
    }

    @JsProxy(value = ProxyA.class, type = { ProxyType.AFTER })
    public void say2() {
        System.out.print("b2");
    }
}

@JsProxy(value = ProxyA.class, type = { ProxyType.BEFORE, ProxyType.AFTER })
class C implements A {
    public void say1() {
        System.out.print("c1");
    }

    public void say2() {
        System.out.print("c2");
    }
}