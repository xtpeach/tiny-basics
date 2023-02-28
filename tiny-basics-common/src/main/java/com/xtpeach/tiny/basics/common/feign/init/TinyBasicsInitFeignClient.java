package com.xtpeach.tiny.basics.common.feign.init;

import com.xtpeach.tiny.basics.common.module.entity.init.TinyBasicsInitHisEntity;
import com.xtpeach.tiny.basics.common.response.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "tiny-basics-api-server-dubbo", path = "/tiny-base-init-his")
public interface TinyBasicsInitFeignClient {

    @GetMapping("/queryList")
    Response<List<TinyBasicsInitHisEntity>> queryList();

}
