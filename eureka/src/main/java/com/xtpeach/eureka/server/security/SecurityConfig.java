package com.xtpeach.eureka.server.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * eureka服务安全
 *
 * @author xtpeach
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 限制所有的外部资源，都只能从当前域名加载
        http.headers().contentSecurityPolicy("default-src 'self'");

        // 关闭Spring-Security的CSRF保护
        http.csrf().disable();

        // 支持httpBasic
        http.authorizeRequests().anyRequest().authenticated().and().httpBasic();
    }

}
