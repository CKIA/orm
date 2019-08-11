package com.ckia.test.enums;

/**
 * @author ckia
 * @description: TODO
 * @title: ckia学习累积使用
 * @projectName orm
 * @date 19-8-11下午10:48
 */
public enum AspectTypeEnums {

    ASPECT_SELECT("saveUser2");

    private String key;

    private AspectTypeEnums() {
    }

    AspectTypeEnums(String key) {
        this.key = key;

    }

    public String getKey() {
        return key;
    }
}
