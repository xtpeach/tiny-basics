package com.xxl.job.core.biz.client;

import com.xxl.job.core.biz.AdminBiz;
import com.xxl.job.core.biz.model.HandleCallbackParam;
import com.xxl.job.core.biz.model.RegistryParam;
import com.xxl.job.core.biz.model.ReturnT;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 任务平台 feign
 *
 * @author xtpeach
 */
@Service
@FeignClient(name = "xxl-job-admin", path = "/api")
public interface AdminBizFeign extends AdminBiz {

    /**
     * feign 调用 callback
     *
     * @param callbackParamList
     * @return
     */
    @PostMapping("/callback")
    @Override
    ReturnT<String> callback(@RequestBody List<HandleCallbackParam> callbackParamList);

}
