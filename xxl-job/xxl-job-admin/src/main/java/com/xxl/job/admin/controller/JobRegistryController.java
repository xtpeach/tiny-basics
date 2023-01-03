package com.xxl.job.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xtpeach.tiny.basics.common.module.entity.xxl.job.XxlJobRegistryEntity;
import com.xtpeach.tiny.basics.core.xxl.job.dao.XxlJobRegistryDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * job registry controller
 * @author xtpeach 2022-11-22 20:52:56
 */
@Controller
@RequestMapping("/jobregistry")
public class JobRegistryController {

    @Resource
    private XxlJobRegistryDao xxlJobRegistryDao;

    @RequestMapping
    public String index(Model model) {
        return "jobregistry/jobregistry.index";
    }

    @PostMapping("/list")
    @ResponseBody
    public List<XxlJobRegistryEntity> list() {

        // list query
        List<XxlJobRegistryEntity> list = xxlJobRegistryDao.selectList(new QueryWrapper<>());

        return list;
    }

}
