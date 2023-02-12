package com.xxl.job.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author xtpeach
 */
@SpringBootApplication(scanBasePackages = {
        // 数据库加密
        "com.xtpeach.tiny.basics.common.config.db",
        // 初始化数据
        "com.xtpeach.tiny.basics.core.init.repo.executor",
        "com.xtpeach.tiny.basics.core.xxl.job.repo.executor",

        // xxl job
        "com.xxl.admin",
        "com.xxl.job"
})
@MapperScan("com.xtpeach.tiny.basics.core.xxl.job.dao")
@EntityScan(basePackages = {
        "com.xtpeach.tiny.basics.common.module.entity.init",
        "com.xtpeach.tiny.basics.common.module.entity.xxl.job"
})
@EnableJpaRepositories(basePackages = {
        "com.xtpeach.tiny.basics.core.init.repo",
        "com.xtpeach.tiny.basics.core.xxl.job.repo"
})
@EnableScheduling
@ServletComponentScan
@EnableTransactionManagement
public class XxlJobAdminApplication {

	public static void main(String[] args) {
        SpringApplication.run(XxlJobAdminApplication.class, args);
	}

}