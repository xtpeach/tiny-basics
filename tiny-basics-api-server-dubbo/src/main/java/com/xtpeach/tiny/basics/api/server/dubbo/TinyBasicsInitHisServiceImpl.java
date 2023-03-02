package com.xtpeach.tiny.basics.api.server.dubbo;

import com.xtpeach.tiny.basics.common.module.entity.init.TinyBasicsInitHisEntity;
import com.xtpeach.tiny.basics.core.api.server.dubbo.TinyBasicsInitHisService;
import com.xtpeach.tiny.basics.core.init.service.TinyBasicsInitHisEntityService;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
import java.util.List;

@DubboService
public class TinyBasicsInitHisServiceImpl implements TinyBasicsInitHisService {

    @Resource
    private TinyBasicsInitHisEntityService tinyBasicsInitHisEntityService;

    @Override
    public List<TinyBasicsInitHisEntity> queryList() {
        return tinyBasicsInitHisEntityService.list();
    }

}
