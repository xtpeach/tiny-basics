package com.xtpeach.tiny.basics.controller.api.server.impl;

import com.xtpeach.tiny.basics.common.module.entity.init.TinyBaseInitHisEntity;
import com.xtpeach.tiny.basics.controller.api.server.TinyBasicsInitHisService;
import com.xtpeach.tiny.basics.core.init.service.TinyBaseInitHisEntityService;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
import java.util.List;

@DubboService(interfaceClass = TinyBasicsInitHisService.class)
public class TinyBasicsInitHisServiceImpl implements TinyBasicsInitHisService {

    @Resource
    private TinyBaseInitHisEntityService tinyBaseInitHisEntityService;

    public List<TinyBaseInitHisEntity> queryList() {
        return tinyBaseInitHisEntityService.list();
    }

}
