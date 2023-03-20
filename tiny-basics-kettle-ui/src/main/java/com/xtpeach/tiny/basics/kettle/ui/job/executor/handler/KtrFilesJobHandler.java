package com.xtpeach.tiny.basics.kettle.ui.job.executor.handler;

import com.xtpeach.tiny.basics.core.kettle.ui.repo.executor.KettleTransformCronTaskEntityRepoExecutor;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class KtrFilesJobHandler {

    private static Logger logger = LoggerFactory.getLogger(KtrFilesJobHandler.class);

    @Value("${spring.application.name:未配置应用名称}")
    private String appName;

    @Value("${ktr.file.path:/home/ktr}")
    private String ktrFilePath;

    @Resource
    private KettleTransformCronTaskEntityRepoExecutor kettleTransformCronTaskEntityRepoExecutor;

    /**
     * ktr 文件执行器
     */
    @XxlJob(value = "ktrFilesJobHandler", cron = "0 0/5 * * * ?")
    public void ktrFilesJobHandler() throws Exception {
        logger.debug("ktrFilesJobHandler > job handler > 任务开始");
        String jobParam = XxlJobHelper.getJobParam();
        XxlJobHelper.log("job handler: {} - {}", appName, "ktrFilesJobHandler");
        XxlJobHelper.log("param: {}", jobParam);

        kettleTransformCronTaskEntityRepoExecutor.run();

        XxlJobHelper.log("result: {}", "[success]");
    }

}
