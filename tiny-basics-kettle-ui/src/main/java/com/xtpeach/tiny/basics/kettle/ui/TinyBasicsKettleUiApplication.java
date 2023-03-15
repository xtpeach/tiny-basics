package com.xtpeach.tiny.basics.kettle.ui;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * [basics ui]
 *
 * @author xtpeach
 */
@SpringBootApplication(scanBasePackages = {
        // 数据库加密
        "com.xtpeach.tiny.basics.common.config.db",
        // 初始化数据
        "com.xtpeach.tiny.basics.core.init.repo.executor",
        "com.xtpeach.tiny.basics.core.init.service",
        "com.xtpeach.tiny.basics.core.ui.repo.executor",

        // ui
        "com.xtpeach.tiny.basics.kettle.ui",

        // xxl job
        "com.xxl.job"
})

/**
 * mapper
 */
@MapperScan(basePackages = {
        // init dao
        "com.xtpeach.tiny.basics.core.init.dao",

        // ui dao
        "com.xtpeach.tiny.basics.core.kettle.ui.dao"
})

/**
 * jpa entity 到 module->entity->具体项目
 */
@EntityScan(basePackages = {
        // init
        "com.xtpeach.tiny.basics.common.module.entity.init",

        // ui
        "com.xtpeach.tiny.basics.common.module.entity.kettle.ui"
})

/**
 * jpa repo
 */
@EnableJpaRepositories(basePackages = {
        // init
        "com.xtpeach.tiny.basics.core.init.repo",

        // ui
        "com.xtpeach.tiny.basics.core.kettle.ui.repo"
})

/**
 * feign client
 */
@EnableFeignClients(basePackages = {
        // tiny basics
        "com.xtpeach.tiny.basics.common.feign",

        // xxl-job
        "com.xxl.job.core.biz.client"
})


@EnableScheduling
@ServletComponentScan
@EnableTransactionManagement
public class TinyBasicsKettleUiApplication {

    /**
     * [start server]
     * Application main(String[] args)
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(TinyBasicsKettleUiApplication.class, args);
    }

}
