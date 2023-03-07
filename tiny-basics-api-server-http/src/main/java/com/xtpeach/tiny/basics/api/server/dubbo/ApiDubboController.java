package com.xtpeach.tiny.basics.api.server.dubbo;

import com.xtpeach.tiny.basics.common.module.entity.init.TinyBasicsInitHisEntity;
import com.xtpeach.tiny.basics.common.response.Response;
import com.xtpeach.tiny.basics.core.api.server.dubbo.TinyBasicsInitHisService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/dubbo")
public class ApiDubboController {

    @DubboReference
    private TinyBasicsInitHisService tinyBasicsInitHisService;

    @PostMapping(value = "/queryInitList")
    public Response<List<TinyBasicsInitHisEntity>> query() {
        return Response.success(tinyBasicsInitHisService.queryList());
    }

}
