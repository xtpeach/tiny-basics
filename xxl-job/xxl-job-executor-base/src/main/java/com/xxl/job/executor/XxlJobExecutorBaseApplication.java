package com.xxl.job.executor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author xuxueli 2018-10-28 00:38:13
 */
@EnableFeignClients(basePackages = {
        "com.xxl.job.core.biz.client"
})
@SpringBootApplication(scanBasePackages = {
        "com.xxl.job"
})
public class XxlJobExecutorBaseApplication {

	public static void main(String[] args) {
        SpringApplication.run(XxlJobExecutorBaseApplication.class, args);
	}

}