package com.ckia.test.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author ckia
 * @description: mybatis 数据源配置
 * @title: ckia学习累积使用
 * @projectName orm
 * @date 19-6-9上午12:58
 */
@Configuration
public class MybatisConfig {

    final static String MAPPER_LOCAL="classpath:com/ckia/test/mapper/*.xml";

    @Bean("mybatis")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource mybatisDataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        return dataSource;
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(mybatisDataSource());
        return transactionManager;
    }

    /**
    　* @description: 创建Mybatis的连接会话工厂实例
    　* @author ckia
    　* @param
    　* @return
    　* @throws
    　* @date 19-6-9 上午2:45
    　*/
    @Bean
    public SqlSessionFactory sqlSessionFactory(@Qualifier("mybatis") DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        /**
         * 设置数据源信息
         */
        factoryBean.setDataSource(dataSource);
        /**
         * 设置mapper文件路径
         */
        Resource[] resource = new PathMatchingResourcePatternResolver().getResources(MybatisConfig.MAPPER_LOCAL);
        factoryBean.setMapperLocations(resource);
        return factoryBean.getObject();
    }
}
