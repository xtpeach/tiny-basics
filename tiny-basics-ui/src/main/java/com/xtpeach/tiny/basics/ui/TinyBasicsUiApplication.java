package com.xtpeach.tiny.basics.ui;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
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
        "com.xtpeach.tiny.basics.core.ui.repo.executor",

        // ui
        "com.xtpeach.tiny.basics.ui"
})
@MapperScan("com.xtpeach.tiny.basics.core.ui.dao")
/**
 * jpa entity 到 module->entity->具体项目
 */
@EntityScan(basePackages = {
        "com.xtpeach.tiny.basics.common.module.entity.init",
        "com.xtpeach.tiny.basics.common.module.entity.ui"
})
@EnableJpaRepositories(basePackages = {
        "com.xtpeach.tiny.basics.core.init.repo",
        "com.xtpeach.tiny.basics.core.ui.repo"
})
@EnableScheduling
@ServletComponentScan
@EnableTransactionManagement
public class TinyBasicsUiApplication {

    /**
     * [start server]
     * Application main(String[] args)
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(TinyBasicsUiApplication.class, args);
    }

}
