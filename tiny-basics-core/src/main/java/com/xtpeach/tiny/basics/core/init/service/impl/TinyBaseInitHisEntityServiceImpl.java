package com.xtpeach.tiny.basics.core.init.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xtpeach.tiny.basics.common.module.entity.init.TinyBasicsInitHisEntity;
import com.xtpeach.tiny.basics.core.init.dao.TinyBasicsInitHisEntityDao;
import com.xtpeach.tiny.basics.core.init.service.TinyBasicsInitHisEntityService;
import org.springframework.stereotype.Service;

/**
 * @author xtpeach
 */
@Service
public class TinyBaseInitHisEntityServiceImpl
        extends ServiceImpl<TinyBasicsInitHisEntityDao, TinyBasicsInitHisEntity>
        implements TinyBasicsInitHisEntityService {
}
