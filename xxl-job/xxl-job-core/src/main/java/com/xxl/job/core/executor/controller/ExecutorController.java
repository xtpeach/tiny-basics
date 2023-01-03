package com.xxl.job.core.executor.controller;

import com.xxl.job.core.biz.ExecutorBiz;
import com.xxl.job.core.biz.impl.ExecutorBizImpl;
import com.xxl.job.core.biz.model.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class ExecutorController {

    private ExecutorBiz executorBiz = new ExecutorBizImpl();

    @PostMapping("/beat")
    @ResponseBody
    public ReturnT<String> beat(){
        return executorBiz.beat();
    }

    @PostMapping("/idleBeat")
    @ResponseBody
    public ReturnT<String> idleBeat(@RequestBody IdleBeatParam idleBeatParam){
        return executorBiz.idleBeat(idleBeatParam);
    }

    @PostMapping("/run")
    @ResponseBody
    public ReturnT<String> run(@RequestBody TriggerParam triggerParam){
        return executorBiz.run(triggerParam);
    }

    @PostMapping("/kill")
    @ResponseBody
    public ReturnT<String> kill(@RequestBody KillParam killParam){
        return executorBiz.kill(killParam);
    }

    @PostMapping("/log")
    @ResponseBody
    public ReturnT<LogResult> log(@RequestBody LogParam logParam){
        return executorBiz.log(logParam);
    }

}
