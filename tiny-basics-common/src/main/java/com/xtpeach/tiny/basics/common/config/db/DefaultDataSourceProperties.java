package com.xtpeach.tiny.basics.common.config.db;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 数据库配置信息
 *
 * @author xtpeach
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
@ConditionalOnProperty(value = "spring.datasource.defaultDb", havingValue = "enable")
public class DefaultDataSourceProperties {

    /**
     * 数据库驱动
     */
    private String driver;

    /**
     * jdbc连接url
     */
    private String url;

    /**
     * 数据库用户（可替换变量名）
     */
    private String repositoryName;

    /**
     * 数据库密码（可替换变量名）
     */
    private String repositoryWord;

}
