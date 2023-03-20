package com.xtpeach.tiny.basics.core.kettle.ui.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xtpeach.tiny.basics.common.module.entity.kettle.ui.KettleTransformCronTaskEntity;
import com.xtpeach.tiny.basics.common.module.vo.kettle.ui.KettleTransformCronTaskVo;
import com.xtpeach.tiny.basics.common.page.PageInfoVo;
import com.xtpeach.tiny.basics.common.param.kettle.ui.KettleTransformCronTaskParam;

public interface KettleTransformCronTaskEntityService
        extends IService<KettleTransformCronTaskEntity> {

    /**
     * 分页查询
     *
     * @param queryParam
     * @return
     */
    PageInfoVo<KettleTransformCronTaskVo> queryByPage(KettleTransformCronTaskParam queryParam);

}
