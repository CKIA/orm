package com.ckia.test.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

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
public class MybatisConfig {

    final static String MAPPER_LOCAL="classpath:com/ckia/test/mapper/*.xml";

    @Bean("mybatis")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource mybatisDataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        return dataSource;
    }
    @Bean("mybatis-2")
    @ConfigurationProperties(prefix = "spring.datasource2")
    public DataSource mybatisDataSource2(){
        DruidDataSource dataSource = new DruidDataSource();
        return dataSource;
    }

    /**
    　* @description: 统一的事务管理对象，数据源是动态绑定的
    　* @author ckia
    　* @param
    　* @return
    　* @throws
    　* @date 19-6-9 下午2:02
    　*/
    @Bean("transactionManager")
    @Primary
    public DataSourceTransactionManager transactionManager() {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dynamicDataSource());
        return transactionManager;
    }

    /**
     * 动态数据库配置
     */
    @Primary
    @Bean(name = "dynamicDataSource")
    public DataSource dynamicDataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        // 默认数据源
        dynamicDataSource.setDefaultTargetDataSource(mybatisDataSource());

        // 配置多数据源
        Map<Object, Object> dsMap = new HashMap(5);
        dsMap.put("mybatis", mybatisDataSource());
        dsMap.put("mybatis-2", mybatisDataSource2());

        dynamicDataSource.setTargetDataSources(dsMap);

        return dynamicDataSource;
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
    @Primary
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dynamicDataSource") DataSource dataSource) throws Exception {
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
