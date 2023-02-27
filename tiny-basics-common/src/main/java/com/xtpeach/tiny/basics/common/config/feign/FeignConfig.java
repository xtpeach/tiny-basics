package com.xtpeach.tiny.basics.common.config.feign;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    /**
     * 日志级别
     * 通过源码可以看到日志等级有 4 种，分别是：
     * NONE: 不输出日志
     * BASICS: 只输出请求方法的 URL 和响应的状态码以及接口执行的时间
     * HEADERS: 将 BASICS 信息和请求头信息输出
     * FULL: 输出完整的请求信息
     *
     * @return
     */
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.BASIC;
    }

}
