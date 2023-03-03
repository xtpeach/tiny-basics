package com.xxl.job.core.feign;

import com.xtpeach.tiny.basics.common.module.entity.xxl.job.XxlJobInfoEntity;
import com.xxl.job.core.biz.model.ReturnT;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@FeignClient(name = "xxl-job-admin", path = "/jobinfo")
public interface JobInfoFeignClient {

    @PostMapping("/pageList")
    @ResponseBody
    Map<String, Object> pageList(@RequestParam(required = false, defaultValue = "0") int start,
                                 @RequestParam(required = false, defaultValue = "10") int length,
                                 String jobInfoId, String jobGroup, int triggerStatus, String jobDesc, String executorHandler, String author);

    @PostMapping("/add")
    @ResponseBody
    ReturnT<String> add(XxlJobInfoEntity jobInfo);

    @PostMapping("/update")
    @ResponseBody
    ReturnT<String> update(XxlJobInfoEntity jobInfo);

    @PostMapping("/remove")
    @ResponseBody
    ReturnT<String> remove(String id);

    @PostMapping("/stop")
    @ResponseBody
    ReturnT<String> pause(String id);

    @PostMapping("/start")
    @ResponseBody
    ReturnT<String> start(String id);

    @PostMapping("/trigger")
    @ResponseBody
    ReturnT<String> triggerJob(String id, String executorParam, String addressList);

}
