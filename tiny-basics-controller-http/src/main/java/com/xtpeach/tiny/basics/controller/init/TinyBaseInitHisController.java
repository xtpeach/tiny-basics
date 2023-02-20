package com.xtpeach.tiny.basics.controller.init;

import com.xtpeach.tiny.basics.common.module.entity.init.TinyBaseInitHisEntity;
import com.xtpeach.tiny.basics.common.response.Response;
import com.xtpeach.tiny.basics.core.init.service.TinyBaseInitHisEntityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xtpeach
 */
@RestController
@RequestMapping("/tiny-base-init-his")
public class TinyBaseInitHisController {

    @Resource
    private TinyBaseInitHisEntityService tinyBaseInitHisEntityService;

    @GetMapping("/queryList")
    public Response<List<TinyBaseInitHisEntity>> queryList() {
        return Response.success(tinyBaseInitHisEntityService.list());
    }

}
