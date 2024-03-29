package com.ckia.test.pojo;

import com.ckia.test.annotation.stringValideation.StringValidation;

/**
 * @author ckia
 * @description: 基本用户类
 * @title: ckia学习累积使用
 * @projectName orm
 * @date 19-6-9上午3:10
 */
public class UserDto {

    private Integer u_id;
    private String u_password;
    private String u_name;

    public Integer getU_id() {
        return u_id;
    }
    public void setU_id(Integer u_id) {
        this.u_id = u_id;
    }

    public String getU_name() {
        return u_name;
    }

    @StringValidation("qqqqqqqqqq")
    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public String getU_password() {
        return u_password;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "u_id=" + u_id +
                ", u_password='" + u_password + '\'' +
                ", u_name='" + u_name + '\'' +
                '}';
    }

    public void setU_password(String u_password) {

        this.u_password = u_password;
    }
}
