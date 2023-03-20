package com.xtpeach.tiny.basics.kettle.ui.controller;

import com.xtpeach.tiny.basics.common.module.entity.xxl.job.XxlJobInfoEntity;
import com.xtpeach.tiny.basics.common.module.vo.kettle.ui.KettleTransformCronTaskVo;
import com.xtpeach.tiny.basics.common.page.PageInfoVo;
import com.xtpeach.tiny.basics.common.param.kettle.ui.KettleTransformCronTaskParam;
import com.xtpeach.tiny.basics.common.response.Response;
import com.xtpeach.tiny.basics.core.kettle.ui.service.impl.KettleTransformCronTaskEntityServiceImpl;
import com.xxl.job.core.feign.JobInfoFeignClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/kettle")
public class KettleController {

    @Resource
    private KettleTransformCronTaskEntityServiceImpl kettleTransformCronTaskEntityService;

    @Resource
    private JobInfoFeignClient jobInfoFeignClient;

    @Value("${spring.application.name:未配置应用名称}")
    private String appName;

    @GetMapping(value = "/queryByPage")
    public Response<PageInfoVo<KettleTransformCronTaskVo>> queryByPage(
            @RequestParam("page") Integer page,
            @RequestParam("limit") Integer limit) {
        KettleTransformCronTaskParam param = new KettleTransformCronTaskParam();
        param.setPage(page);
        param.setRows(limit);

        PageInfoVo<KettleTransformCronTaskVo> kettleTransformCronTaskVoPageInfoVo
                = kettleTransformCronTaskEntityService.queryByPage(param);

        return Response.success(kettleTransformCronTaskVoPageInfoVo);
    }

    @PostMapping(value = "/createTask")
    public Response<String> createTask(@RequestBody KettleTransformCronTaskVo kettleTransformCronTaskVo) {
        XxlJobInfoEntity xxlJobInfoEntity = new XxlJobInfoEntity();
        xxlJobInfoEntity.setTriggerStatus(1);
        xxlJobInfoEntity.setScheduleConf("0 0/5 * * * ?");
        xxlJobInfoEntity.setScheduleType("CRON");
        xxlJobInfoEntity.setAuthor(appName);
        xxlJobInfoEntity.setExecutorHandler("ktrJobHandler");
        xxlJobInfoEntity.setExecutorRouteStrategy(StringUtils.upperCase("WEIGHT"));
        xxlJobInfoEntity.setExecutorBlockStrategy(StringUtils.upperCase("SERIAL_EXECUTION"));
        xxlJobInfoEntity.setGlueRemark("GLUE代码初始化");
        xxlJobInfoEntity.setGlueType("BEAN");
        xxlJobInfoEntity.setJobDesc(appName + "-ktr执行任务");
        xxlJobInfoEntity.setMisfireStrategy(StringUtils.upperCase("DO_NOTHING"));
        xxlJobInfoEntity.setExecutorParam(kettleTransformCronTaskVo.getKtrName());
        xxlJobInfoEntity.setExecutorTimeout(0);
        return Response.success(jobInfoFeignClient.add(xxlJobInfoEntity).getContent());
    }

}
