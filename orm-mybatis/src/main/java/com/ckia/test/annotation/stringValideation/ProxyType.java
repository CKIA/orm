package com.ckia.test.annotation.stringValideation;

/**
 * @author ckia
 * @description: 代理的类型，前还是后
 * @title: ckia学习累积使用
 * @projectName orm
 * @date 19-6-9下午2:37
 */
public enum ProxyType {
    BEFORE(ProxyBase.BEFORE_STRING), AFTER(ProxyBase.AFTER_STRING);
    public String value;

    private ProxyType(String value) {
        this.value = value;
    }

}