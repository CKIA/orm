package com.ckia.test.config;

import com.ckia.test.enums.JPADataSourceType;

/**
 * @author ckia
 * @description: 多数据源上下文配置
 * @title: ckia学习累积使用
 * @projectName orm
 * @date 19-6-9上午5:06
 */
public class DataSourceContextHolder{
    // 默认数据源
    public static final String DEFAULT_DS = JPADataSourceType.MASTER_DATASOURCE_KEY;


    private static final ThreadLocal<String> contextHolder = new ThreadLocal();

    public static void setDB(String dbType) {

        synchronized (DataSourceContextHolder.class){
            contextHolder.set(dbType);
        }
    }

    public static String getDB() {
        return (contextHolder.get());
    }

    public static void clearDB() {
        contextHolder.remove();
    }
}