package com.xxl.job.core.feign;

import com.xtpeach.tiny.basics.common.module.entity.xxl.job.XxlJobInfoEntity;
import com.xxl.job.core.biz.model.ReturnT;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@FeignClient(name = "xxl-job-admin", path = "/feign/jobInfo")
public interface JobFeignClient {

    @PostMapping("/pageList")
    @ResponseBody
    Map<String, Object> pageList(@RequestParam(value = "start", required = false, defaultValue = "0") int start,
                                 @RequestParam(value = "length", required = false, defaultValue = "10") int length,
                                 @RequestParam(value = "jobInfoId") String jobInfoId,
                                 @RequestParam(value = "jobGroup", defaultValue = "-1") String jobGroup,
                                 @RequestParam(value = "triggerStatus", defaultValue = "-1") int triggerStatus,
                                 @RequestParam(value = "jobDesc") String jobDesc,
                                 @RequestParam(value = "executorHandler") String executorHandler,
                                 @RequestParam(value = "author") String author,
                                 @RequestParam(value = "executorParam") String executorParam);

    @PostMapping("/add")
    @ResponseBody
    ReturnT<String> add(@RequestBody XxlJobInfoEntity jobInfo);

    @PostMapping("/update")
    @ResponseBody
    ReturnT<String> update(@RequestBody XxlJobInfoEntity jobInfo);

    @PostMapping("/remove")
    @ResponseBody
    ReturnT<String> remove(@RequestParam("id") String id);

    @PostMapping("/stop")
    @ResponseBody
    ReturnT<String> pause(@RequestParam("id") String id);

    @PostMapping("/start")
    @ResponseBody
    ReturnT<String> start(@RequestParam("id") String id);

    @PostMapping("/trigger")
    @ResponseBody
    ReturnT<String> triggerJob(@RequestParam("id") String id,
                               @RequestParam("executorParam") String executorParam,
                               @RequestParam("addressList") String addressList);

}
