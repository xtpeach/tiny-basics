package com.xtpeach.tiny.basics.api.server.restful;

import com.xtpeach.tiny.basics.common.module.entity.init.TinyBaseInitHisEntity;
import com.xtpeach.tiny.basics.common.response.Response;
import com.xtpeach.tiny.basics.controller.api.server.TinyBasicsInitHisService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/dubbo")
public class ApiTinyRestfulDubboController {

    @DubboReference(interfaceClass  = TinyBasicsInitHisService.class)
    private TinyBasicsInitHisService tinyBasicsInitHisService;

    @PostMapping(value = "/queryInitList")
    public Response<List<TinyBaseInitHisEntity>> query() {
        return Response.success(tinyBasicsInitHisService.queryList());
    }

}
