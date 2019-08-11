package com.ckia.test.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.ckia.test.enums.JPADataSourceType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ckia
 * @description: mybatis 数据源配置（包含一个动态配置数据的操作）
 * @title: ckia学习累积使用
 * @projectName orm
 * @date 19-6-9上午12:58
 */
@Configuration
public class DataSourceConfig {

    @Bean(value = JPADataSourceType.MASTER_DATASOURCE_KEY)
    @Qualifier(JPADataSourceType.MASTER_DATASOURCE_KEY)
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource masterSource(){
        DruidDataSource dataSource = new DruidDataSource();
        return dataSource;
    }

    @Bean(value = JPADataSourceType.SLAVE_DATASOURCE_KEY)
    @Qualifier(JPADataSourceType.SLAVE_DATASOURCE_KEY)
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource slaveDataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        return dataSource;
    }

    /**
     * 动态数据库配置
     */
    @Primary
    @Bean(name = "jpaDynamicDataSource")
    public DataSource dynamicDataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        // 默认数据源
        dynamicDataSource.setDefaultTargetDataSource(masterSource());

        // 配置多数据源
        Map<Object, Object> dsMap = new HashMap(2);
        dsMap.put(JPADataSourceType.MASTER_DATASOURCE_KEY, masterSource());
        dsMap.put(JPADataSourceType.SLAVE_DATASOURCE_KEY, slaveDataSource());

        dynamicDataSource.setTargetDataSources(dsMap);

        return dynamicDataSource;
    }
}
