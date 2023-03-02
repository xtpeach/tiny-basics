package com.xxl.job.executor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author xtpeach
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