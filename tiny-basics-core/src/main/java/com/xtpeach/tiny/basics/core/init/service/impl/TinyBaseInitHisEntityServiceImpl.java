package com.xtpeach.tiny.basics.core.init.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xtpeach.tiny.basics.common.module.entity.init.TinyBaseInitHisEntity;
import com.xtpeach.tiny.basics.core.init.dao.TinyBaseInitHisEntityDao;
import com.xtpeach.tiny.basics.core.init.service.TinyBaseInitHisEntityService;
import org.springframework.stereotype.Service;

/**
 * @author xtpeach
 */
@Service
public class TinyBaseInitHisEntityServiceImpl
        extends ServiceImpl<TinyBaseInitHisEntityDao, TinyBaseInitHisEntity>
        implements TinyBaseInitHisEntityService {
}
