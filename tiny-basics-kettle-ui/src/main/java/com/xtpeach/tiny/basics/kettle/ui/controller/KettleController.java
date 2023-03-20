package com.xtpeach.tiny.basics.kettle.ui.controller;

import com.xtpeach.tiny.basics.common.module.vo.kettle.ui.KettleTransformCronTaskVo;
import com.xtpeach.tiny.basics.common.page.PageInfoVo;
import com.xtpeach.tiny.basics.common.param.kettle.ui.KettleTransformCronTaskParam;
import com.xtpeach.tiny.basics.common.response.Response;
import com.xtpeach.tiny.basics.core.kettle.ui.service.impl.KettleTransformCronTaskEntityServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/kettle")
public class KettleController {

    @Resource
    private KettleTransformCronTaskEntityServiceImpl kettleTransformCronTaskEntityService;

    @GetMapping(value = "/queryByPage")
    public Response<PageInfoVo<KettleTransformCronTaskVo>> queryByPage(
            @RequestParam("page") Integer page,
            @RequestParam("limit") Integer limit
    ) {

        KettleTransformCronTaskParam param = new KettleTransformCronTaskParam();
        param.setPage(page);
        param.setRows(limit);

        PageInfoVo<KettleTransformCronTaskVo> kettleTransformCronTaskVoPageInfoVo
                = kettleTransformCronTaskEntityService.queryByPage(param);

        return Response.success(kettleTransformCronTaskVoPageInfoVo);

    }

}
