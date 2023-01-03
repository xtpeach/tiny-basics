package com.xtpeach.tiny.basics.common.config.db;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.xtpeach.tiny.basics.common.util.db.DecodeUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
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
     * Bean default-database
     *
     * @return
     */
    @Bean(name = "default-database")
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .driverClassName(defaultDataSourceProperties.getDriver())
                .url(DecodeUtils.decodeUrl(defaultDataSourceProperties.getUrl()))
                .username(DecodeUtils.decodeUsername(defaultDataSourceProperties.getRepositoryName()))
                .password(DecodeUtils.decodePassword(defaultDataSourceProperties.getRepositoryWord()))
                .build();
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
