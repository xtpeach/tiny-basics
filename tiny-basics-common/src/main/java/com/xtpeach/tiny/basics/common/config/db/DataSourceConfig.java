package com.xtpeach.tiny.basics.common.config.db;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * 数据源配置
 *
 * @author xtpeach
 */
@Configuration
@EnableConfigurationProperties(MybatisPlusProperties.class)
@ConditionalOnProperty(value = "spring.datasource.defaultDb", havingValue = "enable")
public class DataSourceConfig {

    /**
     * mybatis-plus 配置信息
     */
    @Resource
    private MybatisPlusProperties mybatisPlusProperties;

    /**
     * 自定义数据库配置信息
     */
    @Resource
    private DefaultDataSourceProperties defaultDataSourceProperties;

    /**
     * hikari 数据库连接池配置
     */
    @Resource
    private HikariProperties hikariProperties;

    /**
     * Bean default-database
     *
     * @return
     */
    @Bean(name = "default-database")
    public DataSource dataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        // base config
        hikariConfig.setDriverClassName(defaultDataSourceProperties.getDriver());
        hikariConfig.setJdbcUrl(defaultDataSourceProperties.getUrl());

        // 数据库用户名加密
        hikariConfig.setUsername(defaultDataSourceProperties.getRepositoryName());
        // 数据库密码加密
        hikariConfig.setPassword(defaultDataSourceProperties.getRepositoryWord());

        // hikari config
        hikariConfig.setMinimumIdle(hikariProperties.getMinimumIdle());
        hikariConfig.setMaximumPoolSize(hikariProperties.getMaximumPoolSize());
        hikariConfig.setAutoCommit(hikariProperties.getAutoCommit());
        hikariConfig.setIdleTimeout(hikariProperties.getIdleTimeout());
        hikariConfig.setPoolName(hikariProperties.getPoolName());
        hikariConfig.setMaxLifetime(hikariProperties.getMaxLifetime());
        hikariConfig.setConnectionTimeout(hikariProperties.getConnectionTimeout());
        hikariConfig.setConnectionTestQuery(hikariProperties.getConnectionTestQuery());
        hikariConfig.setValidationTimeout(hikariProperties.getValidationTimeout());
        HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);

        return hikariDataSource;
    }

    /**
     * Bean sqlSessionFactory
     *
     * @return
     */
    @Bean("sqlSessionFactory")
    public MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean() {
        MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        mybatisSqlSessionFactoryBean.setDataSource(dataSource());
        mybatisSqlSessionFactoryBean.setMapperLocations(mybatisPlusProperties.resolveMapperLocations());
        mybatisSqlSessionFactoryBean.setTypeAliasesPackage(mybatisPlusProperties.getTypeAliasesPackage());
        mybatisSqlSessionFactoryBean.setConfigLocation((org.springframework.core.io.Resource) mybatisPlusProperties.getConfiguration());
        return mybatisSqlSessionFactoryBean;
    }

}
