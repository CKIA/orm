package com.ckia.test.config;

/**
 * @author ckia
 * @description: 动态数据源配置
 * @title: ckia学习累积使用
 * @projectName orm
 * @date 19-6-9上午5:08
 */
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getDB();
    }
}