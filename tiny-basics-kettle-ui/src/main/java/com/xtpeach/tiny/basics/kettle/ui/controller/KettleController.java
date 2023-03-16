package com.xtpeach.tiny.basics.kettle.ui.controller;

import com.xtpeach.tiny.basics.common.module.core.UUIDStrGenerator;
import com.xtpeach.tiny.basics.common.module.vo.kettle.ui.KettleTransformCronTaskVo;
import com.xtpeach.tiny.basics.common.page.PageInfoVo;
import com.xtpeach.tiny.basics.common.param.kettle.ui.KettleTransformCronTaskParam;
import com.xtpeach.tiny.basics.common.response.Response;
import com.xtpeach.tiny.basics.common.util.page.PageHelperUtil;
import org.apache.commons.compress.utils.Lists;
import org.joda.time.DateTime;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/kettle")
public class KettleController {

    @GetMapping(value = "/queryByPage")
    public Response<PageInfoVo<KettleTransformCronTaskVo>> queryByPage(
            @RequestParam("page") Integer page,
            @RequestParam("limit") Integer limit
    ) {

        KettleTransformCronTaskParam param = new KettleTransformCronTaskParam();
        param.setPage(page);
        param.setRows(limit);

        List<KettleTransformCronTaskVo> kettleTransformCronTaskVoList = Lists.newArrayList();
        KettleTransformCronTaskVo kettleTransformCronTaskVo = new KettleTransformCronTaskVo();
        kettleTransformCronTaskVo.setId(UUIDStrGenerator.generate());
        kettleTransformCronTaskVo.setCreateTime(DateTime.now().toDate());
        kettleTransformCronTaskVo.setUpdateTime(DateTime.now().toDate());
        kettleTransformCronTaskVo.setKtrName("random_num.ktr");
        kettleTransformCronTaskVo.setKtrPath("/random_num.ktr");
        kettleTransformCronTaskVo.setXxlJobTaskId(UUIDStrGenerator.generate());
        kettleTransformCronTaskVoList.add(kettleTransformCronTaskVo);

        return Response.success(PageHelperUtil.autoPage(param, kettleTransformCronTaskVoList));

    }

}
