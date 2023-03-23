package com.xtpeach.tiny.basics.kettle.ui.controller;

import com.xtpeach.tiny.basics.common.module.entity.kettle.ui.KettleTransformCronTaskEntity;
import com.xtpeach.tiny.basics.common.module.entity.xxl.job.XxlJobInfoEntity;
import com.xtpeach.tiny.basics.common.module.vo.kettle.ui.KettleTransformCronTaskVo;
import com.xtpeach.tiny.basics.common.page.PageInfoVo;
import com.xtpeach.tiny.basics.common.param.kettle.ui.KettleTransformCronTaskParam;
import com.xtpeach.tiny.basics.common.response.Response;
import com.xtpeach.tiny.basics.core.kettle.ui.service.KettleTransformCronTaskEntityService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.feign.JobFeignClient;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.File;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/kettle")
public class KettleController {

    @Value("${ktr.file.path:/home/ktr}")
    private String ktrFilePath;

    @Value("${spring.application.name:未配置应用名称}")
    private String appName;

    @Resource
    private JobFeignClient jobFeignClient;

    @Resource
    private KettleTransformCronTaskEntityService kettleTransformCronTaskEntityService;

    @PostMapping(value = "/queryByPage")
    public Response<PageInfoVo<KettleTransformCronTaskVo>> queryByPage(@RequestParam("page") Integer page,
                                                                       @RequestParam("limit") Integer limit,
                                                                       @RequestParam(value = "id", required = false) String id,
                                                                       @RequestParam(value = "xxlJobTaskId", required = false) String xxlJobTaskId,
                                                                       @RequestParam(value = "ktrName", required = false) String ktrName,
                                                                       @RequestParam(value = "ktrDesc", required = false) String ktrDesc,
                                                                       @RequestParam(value = "createTime", required = false) String createTime,
                                                                       @RequestParam(value = "updateTime", required = false) String updateTime) {
        KettleTransformCronTaskParam param = new KettleTransformCronTaskParam();
        param.setPage(page);
        param.setRows(limit);
        param.setId(id);
        param.setXxlJobTaskId(xxlJobTaskId);
        param.setKtrName(ktrName);
        param.setKtrDesc(ktrDesc);
        param.setCreateTime(createTime);
        param.setUpdateTime(updateTime);
        PageInfoVo<KettleTransformCronTaskVo> kettleTransformCronTaskVoPageInfoVo
                = kettleTransformCronTaskEntityService.queryByPage(param);
        return Response.success(kettleTransformCronTaskVoPageInfoVo);
    }

    @PostMapping(value = "/createTask")
    public Response<String> createTask(@RequestBody KettleTransformCronTaskVo kettleTransformCronTaskVo) {
        KettleTransformCronTaskEntity kettleTransformCronTaskEntity = kettleTransformCronTaskEntityService.getById(kettleTransformCronTaskVo.getId());
        if (ObjectUtils.isNotEmpty(kettleTransformCronTaskEntity)
                && StringUtils.isNotBlank(kettleTransformCronTaskEntity.getXxlJobTaskId())) {
            jobFeignClient.remove(kettleTransformCronTaskEntity.getXxlJobTaskId());
        }
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
        xxlJobInfoEntity.setJobDesc(appName + " - ktr 执行任务 - " + kettleTransformCronTaskVo.getKtrName());
        xxlJobInfoEntity.setMisfireStrategy(StringUtils.upperCase("DO_NOTHING"));
        xxlJobInfoEntity.setExecutorParam(kettleTransformCronTaskVo.getKtrName());
        xxlJobInfoEntity.setExecutorTimeout(0);
        xxlJobInfoEntity.setJobGroup(appName);
        ReturnT<String> returnT = jobFeignClient.add(xxlJobInfoEntity);
        if (ReturnT.SUCCESS_CODE == returnT.getCode()) {
            String xxlJobId = returnT.getContent();
            kettleTransformCronTaskEntity.setXxlJobTaskId(xxlJobId);
            kettleTransformCronTaskEntityService.updateById(kettleTransformCronTaskEntity);
            return Response.success("创建任务成功，任务编码：" + xxlJobId);
        } else {
            return Response.fail("创建任务失败，" + returnT.getMsg());
        }
    }

    @PostMapping(value = "/update")
    public Response<Boolean> update(@RequestBody KettleTransformCronTaskVo kettleTransformCronTaskVo) {
        if (StringUtils.isNotBlank(kettleTransformCronTaskVo.getId())) {
            KettleTransformCronTaskEntity kettleTransformCronTaskEntity = kettleTransformCronTaskEntityService.getById(kettleTransformCronTaskVo.getId());
            if (ObjectUtils.isNotEmpty(kettleTransformCronTaskEntity)) {
                kettleTransformCronTaskEntity.setKtrDesc(kettleTransformCronTaskVo.getKtrDesc());
                kettleTransformCronTaskEntity.setUpdateTime(DateTime.now().toDate());
                return Response.success(kettleTransformCronTaskEntityService.updateById(kettleTransformCronTaskEntity));
            } else {
                return Response.fail("更新失败，该对象不存在");
            }
        } else {
            return Response.fail("更新失败，对象ID为空");
        }
    }

    @PostMapping(value = "/delete")
    public Response<Boolean> delete(@RequestBody KettleTransformCronTaskVo kettleTransformCronTaskVo) {
        if (StringUtils.isNotBlank(kettleTransformCronTaskVo.getId())) {
            KettleTransformCronTaskEntity originalKettleTransformCronTaskEntity
                    = kettleTransformCronTaskEntityService.getById(kettleTransformCronTaskVo.getId());
            if (StringUtils.equals(kettleTransformCronTaskVo.getKtrName(), originalKettleTransformCronTaskEntity.getKtrName())) {
                if (StringUtils.isNotBlank(originalKettleTransformCronTaskEntity.getXxlJobTaskId())) {
                    jobFeignClient.remove(originalKettleTransformCronTaskEntity.getXxlJobTaskId());
                }
                kettleTransformCronTaskEntityService.removeById(kettleTransformCronTaskVo.getId());
                List<File> fileList = Arrays.asList(new File(ktrFilePath).listFiles());
                fileList.stream().forEach(file -> {
                    if (StringUtils.equals(file.getName(), kettleTransformCronTaskVo.getKtrName())) {
                        FileUtils.deleteQuietly(file);
                    }
                });
                return Response.success(true);
            } else {
                return Response.fail("删除失败，文件名不存在");
            }
        } else {
            return Response.fail("删除失败，对象ID为空");
        }
    }

}
