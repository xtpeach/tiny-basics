package com.xtpeach.tiny.basics.kettle.ui.job.executor.handler;

import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KettleXxlJob {

    private static Logger logger = LoggerFactory.getLogger(KettleXxlJob.class);

    @Value("${spring.application.name:未配置应用名称}")
    private String appName;

    /**
     * 示例 处理器
     */
    @XxlJob("kettleJobHandler")
    public void kettleJobHandler() throws Exception {
        logger.debug("kettleJobHandler > job handler > 任务开始");
        String jobParam = XxlJobHelper.getJobParam();
        XxlJobHelper.log("job handler: {} - {}", appName, "kettleJobHandler");
        XxlJobHelper.log("param: {}", jobParam);

        KettleEnvironment.init();
        TransMeta transMeta = new TransMeta("D:\\desktop\\random_num.ktr");
        Trans trans = new Trans(transMeta);
        trans.execute(null);
        trans.waitUntilFinished();

        XxlJobHelper.log("result: {}", "[success]");
    }

}
