package com.xtpeach.tiny.basics.core.kettle.ui.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xtpeach.tiny.basics.common.module.entity.kettle.ui.KettleTransformCronTaskEntity;
import com.xtpeach.tiny.basics.common.module.vo.kettle.ui.KettleTransformCronTaskVo;
import com.xtpeach.tiny.basics.common.page.PageInfoVo;
import com.xtpeach.tiny.basics.common.param.kettle.ui.KettleTransformCronTaskParam;
import com.xtpeach.tiny.basics.common.util.page.PageHelperUtil;
import com.xtpeach.tiny.basics.core.kettle.ui.dao.KettleTransformCronTaskEntityDao;
import com.xtpeach.tiny.basics.core.kettle.ui.service.KettleTransformCronTaskEntityService;
import org.springframework.stereotype.Service;

/**
 * @author xtpeach
 */
@Service
public class KettleTransformCronTaskEntityServiceImpl
        extends ServiceImpl<KettleTransformCronTaskEntityDao, KettleTransformCronTaskEntity>
        implements KettleTransformCronTaskEntityService {

    /**
     * 分页查询
     *
     * @param queryParam
     * @return
     */
    @Override
    public PageInfoVo<KettleTransformCronTaskVo> queryByPage(KettleTransformCronTaskParam queryParam) {
        // 查询分页数据
        return PageHelperUtil.doSelect(queryParam,
                () -> baseMapper.selectList(queryParam.getQueryWrapper()),
                v -> KettleTransformCronTaskVo.of((KettleTransformCronTaskEntity) v));
    }

}
