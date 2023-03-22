package com.xtpeach.tiny.basics.kettle.ui.job.executor.handler;

import com.alibaba.fastjson.JSON;
import com.xtpeach.tiny.basics.common.module.entity.kettle.ui.KettleTransformCronTaskEntity;
import com.xtpeach.tiny.basics.core.kettle.ui.repo.KettleTransformCronTaskEntityRepo;
import com.xtpeach.tiny.basics.core.kettle.ui.repo.executor.KettleTransformCronTaskEntityRepoExecutor;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class KtrFilesJobHandler {

    private static Logger logger = LoggerFactory.getLogger(KtrFilesJobHandler.class);

    @Value("${spring.application.name:未配置应用名称}")
    private String appName;

    @Resource
    private KettleTransformCronTaskEntityRepo kettleTransformCronTaskEntityRepo;

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

        // 扫描文件夹，获取所有 ktr 文件并将记录插入数据库
        kettleTransformCronTaskEntityRepoExecutor.run();

        // 添加日志打印
        List<KettleTransformCronTaskEntity> kettleTransformCronTaskEntityList = kettleTransformCronTaskEntityRepo.findAll();
        if (CollectionUtils.isNotEmpty(kettleTransformCronTaskEntityList)) {
            kettleTransformCronTaskEntityList.stream().forEach(kettleTransformCronTaskEntity
                    -> XxlJobHelper.log("result: {}", JSON.toJSONString(kettleTransformCronTaskEntity, true)));
        }

        XxlJobHelper.log("result: {}", "[success]");
    }

}
