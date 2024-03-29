package com.ckia.test.entity;

import javax.persistence.*;

/**
 * @author ckia
 * @description: 基本用户类
 * @title: ckia学习累积使用
 * @projectName orm
 * @date 19-6-9上午3:10
 */
@Entity
@Table(name = "t_user")
public class UserDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer u_id;
    private String u_password;
    private String u_name;

    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name="u_id",referencedColumnName = "l_userId")
    private LoggerDto log;

    public LoggerDto getLog() {
        return log;
    }

    public void setLog(LoggerDto log) {
        this.log = log;
    }

    public Integer getU_id() {
        return u_id;
    }
    public void setU_id(Integer u_id) {
        this.u_id = u_id;
    }

    public String getU_name() {
        return u_name;
    }

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
                ", log='" + log + '\'' +
                '}';
    }

    public void setU_password(String u_password) {

        this.u_password = u_password;
    }
}
