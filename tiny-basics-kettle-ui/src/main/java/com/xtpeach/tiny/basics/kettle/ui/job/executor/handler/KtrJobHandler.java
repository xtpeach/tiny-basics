package com.xtpeach.tiny.basics.kettle.ui.job.executor.handler;

import com.xtpeach.tiny.basics.common.module.entity.kettle.ui.KettleTransformCronTaskEntity;
import com.xtpeach.tiny.basics.core.kettle.ui.repo.KettleTransformCronTaskEntityRepo;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class KtrJobHandler {

    private static Logger logger = LoggerFactory.getLogger(KtrJobHandler.class);

    @Value("${spring.application.name:未配置应用名称}")
    private String appName;

    @Resource
    private KettleTransformCronTaskEntityRepo kettleTransformCronTaskEntityRepo;

    /**
     * ktr 文件执行器
     */
    @XxlJob(value = "ktrJobHandler")
    public void ktrJobHandler() throws Exception {
        logger.debug("ktrJobHandler > job handler > 任务开始");
        String jobParam = XxlJobHelper.getJobParam();
        XxlJobHelper.log("job handler: {} - {}", appName, "ktrJobHandler");
        XxlJobHelper.log("param: {}", jobParam);

        KettleTransformCronTaskEntity kettleTransformCronTaskEntity = kettleTransformCronTaskEntityRepo.findByKtrName(jobParam);

        KettleEnvironment.init();
        TransMeta transMeta = new TransMeta(kettleTransformCronTaskEntity.getKtrPath());
        Trans trans = new Trans(transMeta);
        trans.execute(null);
        trans.waitUntilFinished();

        XxlJobHelper.log("result: {}", "[success]");
    }

}
