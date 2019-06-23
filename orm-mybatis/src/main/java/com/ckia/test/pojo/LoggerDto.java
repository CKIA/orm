package com.ckia.test.pojo;


/**
 * @author ckia
 * @description: 基本日志记录类
 * @title: ckia学习累积使用
 * @projectName orm
 * @date 19-6-9上午3:10
 */
public class LoggerDto {

    private Integer l_id;
    private Integer l_type;
    private String l_detail;

    public Integer getL_id() {
        return l_id;
    }

    public void setL_id(Integer l_id) {
        this.l_id = l_id;
    }

    public Integer getL_type() {
        return l_type;
    }

    public void setL_type(Integer l_type) {
        this.l_type = l_type;
    }

    public String getL_detail() {
        return l_detail;
    }

    public void setL_detail(String l_detail) {
        this.l_detail = l_detail;
    }

    @Override
    public String toString() {
        return "LoggerDto{" +
                "l_id=" + l_id +
                ", l_type='" + l_type + '\'' +
                ", l_detail='" + l_detail + '\'' +
                '}';
    }
}
