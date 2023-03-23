package com.xtpeach.tiny.basics.kettle.ui.templates.kettle;

import com.xtpeach.tiny.basics.common.module.entity.kettle.ui.KettleTransformCronTaskEntity;
import com.xtpeach.tiny.basics.core.kettle.ui.service.KettleTransformCronTaskEntityService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/**
 * @author xtpeach
 */
@Controller
@RequestMapping("/kettle")
public class KettleIndexPageController {

    @Resource
    private KettleTransformCronTaskEntityService kettleTransformCronTaskEntityService;

    /**
     * 主页
     *
     * @return
     */
    @GetMapping(value = "/page")
    public String index() {
        return "kettle/index";
    }

    /**
     * 编辑
     *
     * @return
     */
    @GetMapping(value = "/edit")
    public String edit(Model model, @RequestParam(name = "id") String id) {
        KettleTransformCronTaskEntity kettleTransformCronTaskEntity = kettleTransformCronTaskEntityService.getById(id);
        if (ObjectUtils.isNotEmpty(kettleTransformCronTaskEntity)) {
            model.addAttribute("id", id);
            model.addAttribute("ktrDesc", kettleTransformCronTaskEntity.getKtrDesc());
        }
        return "kettle/edit";
    }

}
