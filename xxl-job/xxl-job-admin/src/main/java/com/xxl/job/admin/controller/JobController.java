package com.xxl.job.admin.controller;

import com.xtpeach.tiny.basics.common.module.entity.xxl.job.XxlJobInfoEntity;
import com.xtpeach.tiny.basics.core.xxl.job.dao.XxlJobGroupDao;
import com.xxl.admin.core.thread.JobTriggerPoolHelper;
import com.xxl.admin.core.trigger.TriggerTypeEnum;
import com.xxl.job.admin.controller.annotation.PermissionLimit;
import com.xxl.job.admin.service.XxlJobService;
import com.xxl.job.core.biz.model.ReturnT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * index controller
 *
 * @author xtpeach
 */
@Controller
@RequestMapping("/feign/jobInfo")
public class JobController {

    private static Logger logger = LoggerFactory.getLogger(JobController.class);

    @Resource
    private XxlJobService xxlJobService;

    @PermissionLimit(limit = false)
    @PostMapping("/pageList")
    @ResponseBody
    public Map<String, Object> pageList(@RequestParam(value = "start", required = false, defaultValue = "0") int start,
                                        @RequestParam(value = "length", required = false, defaultValue = "10") int length,
                                        @RequestParam(value = "jobInfoId") String jobInfoId,
                                        @RequestParam(value = "jobGroup", defaultValue = "-1") String jobGroup,
                                        @RequestParam(value = "triggerStatus", defaultValue = "-1") int triggerStatus,
                                        @RequestParam(value = "jobDesc") String jobDesc,
                                        @RequestParam(value = "executorHandler") String executorHandler,
                                        @RequestParam(value = "author") String author,
                                        @RequestParam(value = "executorParam") String executorParam) {
        return xxlJobService.pageList(start, length, jobInfoId, jobGroup, triggerStatus, jobDesc, executorHandler, author, executorParam);
    }

    @PermissionLimit(limit = false)
    @PostMapping("/add")
    @ResponseBody
    public ReturnT<String> add(@RequestBody XxlJobInfoEntity jobInfo) {
        return xxlJobService.add(jobInfo);
    }

    @PermissionLimit(limit = false)
    @PostMapping("/update")
    @ResponseBody
    public ReturnT<String> update(@RequestBody XxlJobInfoEntity jobInfo) {
        return xxlJobService.update(jobInfo);
    }

    @PermissionLimit(limit = false)
    @PostMapping("/remove")
    @ResponseBody
    public ReturnT<String> remove(@RequestParam(value = "id") String id) {
        return xxlJobService.remove(id);
    }

    @PermissionLimit(limit = false)
    @PostMapping("/stop")
    @ResponseBody
    public ReturnT<String> pause(@RequestParam(value = "id") String id) {
        return xxlJobService.stop(id);
    }

    @PermissionLimit(limit = false)
    @PostMapping("/start")
    @ResponseBody
    public ReturnT<String> start(@RequestParam(value = "id") String id) {
        return xxlJobService.start(id);
    }

    @PermissionLimit(limit = false)
    @PostMapping("/trigger")
    @ResponseBody
    public ReturnT<String> trigger(@RequestParam(value = "id") String id,
                                   @RequestParam(value = "executorParam") String executorParam,
                                   @RequestParam(value = "addressList") String addressList) {
        if (executorParam == null) {
            executorParam = "";
        }
        JobTriggerPoolHelper.trigger(id, TriggerTypeEnum.MANUAL, -1, null, executorParam, addressList);
        return ReturnT.SUCCESS;
    }

}
