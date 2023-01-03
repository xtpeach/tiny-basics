package com.xxl.job.executor.service.jobhandler;

import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * XxlJob开发示例（Bean模式）
 *
 * @author xuxueli 2019-12-11 21:52:51
 */
@Component
public class SampleXxlJob {

    @Value("${spring.application.name:未配置应用名称}")
    private String appName;

    private static Logger logger = LoggerFactory.getLogger(SampleXxlJob.class);

    /**
     * 示例 处理器
     */
    @XxlJob("demoJobHandler")
    public void demoJobHandler() throws Exception {
        logger.debug("demoJobHandler > job handler > 任务开始");
        String jobParam = XxlJobHelper.getJobParam();
        XxlJobHelper.log("job handler: {} - {}", appName, "demoJobHandler");
        XxlJobHelper.log("param: {}", jobParam);

        for (int i = 0; i < 5; i++) {
            XxlJobHelper.log("beat at:" + i);
            TimeUnit.SECONDS.sleep(2);
        }
        // default success

        XxlJobHelper.log("result: {}", "[success]");
    }

    /**
     * 测试 处理器
     */
    @XxlJob("testJobHandler")
    public void testJobHandler() throws Exception {
        logger.debug("testJobHandler > job handler > 任务开始");
        String jobParam = XxlJobHelper.getJobParam();
        XxlJobHelper.log("job handler: {}", "testJobHandler");
        XxlJobHelper.log("param: {}", jobParam);

        for (int i = 0; i < 5; i++) {
            XxlJobHelper.log("beat at:" + i);
            TimeUnit.SECONDS.sleep(2);
        }
        // default success

        XxlJobHelper.log("result: {}", "[success]");
    }

}
